package ar.uba.fi.ingsoft1.todo_template.team;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/teams")
@Tag(name = "Teams")
public class TeamRestController {
    private final TeamService teamService;

    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping(consumes = "application/json",
                produces = "application/json")
    @Operation(summary = "Create a new team")
    @ApiResponse(responseCode = "201",
                description = "Team created successfully",
                content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400",
                description = "Team with that name already exists",
                content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeamDTO> createTeam(
        @Valid
        @RequestBody
        TeamCreateDTO teamCreateDTO
    ) {
        try {
            TeamDTO teamDTO = teamService.createTeam(teamCreateDTO);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/",
                produces = "application/json")
    @Operation(summary = "Search for a team by name")
    @ApiResponse(responseCode = "201",
                description = "Team obtained successfully")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamDTO> searchTeam(
        @Valid
        String name
    ) {
        try {
            TeamDTO teamDTO = teamService.searchTeam(name);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Search for teams")
    @ApiResponse(responseCode = "201",
                description = "Teams obtained successfully")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeamDTO>> searchTeams() {
        List<TeamDTO> teams = teamService.searchTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PatchMapping(consumes = "application/json",
                produces = "application/json")
    @Operation(summary = "Update a team")
    @ApiResponse(responseCode = "200",
                description = "Team updated successfully")
    @ApiResponse(responseCode = "400",
                description = "Team with that name already exists")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamDTO> updateTeam(
        @Valid
        String name,
        @Valid
        @RequestBody
        TeamCreateDTO teamCreateDTO
    ) {
        try {
            TeamDTO teamDTO = teamService.updateTeams(name, teamCreateDTO);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping(value = "/")
    @Operation(summary = "Delete a team")
    @ApiResponse(responseCode = "204",
                description = "Team deleted successfully")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Team> delateTeam(
        @Valid
        String name
    ) {
        boolean delated = teamService.deleteTeam(name);
        
        if (!delated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
