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
    @ApiResponse(responseCode = "409",
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
            return new ResponseEntity<>(teamDTO, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/",
                produces = "application/json")
    @Operation(summary = "Search for a team by name")
    @ApiResponse(responseCode = "200",
                description = "Team obtained successfully")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamDTO> getTeam(
        @Valid
        String teamName
    ) {
        try {
            TeamDTO teamDTO = teamService.getTeam(teamName);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Search for teams")
    @ApiResponse(responseCode = "200",
                description = "Teams obtained successfully")
    @ApiResponse(responseCode = "404",
                description = "Teams not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeamDTO>> getTeams() {
        try {
            List<TeamDTO> teams = teamService.getTeams();
            return new ResponseEntity<>(teams, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(consumes = "application/json",
                produces = "application/json")
    @Operation(summary = "Update a team")
    @ApiResponse(responseCode = "200",
                description = "Team updated successfully")
    @ApiResponse(responseCode = "409",
                description = "Team with that name already exists")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamDTO> updateTeam(
        @Valid
        String teamName,
        @Valid
        @RequestBody
        TeamCreateDTO teamCreateDTO
    ) {
        try {
            TeamDTO teamDTO = teamService.updateTeams(teamName, teamCreateDTO);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }

    @PatchMapping(value = "/")
    @Operation(summary = "Add player to a team")
    @ApiResponse(responseCode = "200",
                description = "Added player to team successfully")
    @ApiResponse(responseCode = "409",
                description = "Player already exists in the team")
    @ApiResponse(responseCode = "400",
                description = "Team is full")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addplayer(
        @Valid
        String teamName,
        @Valid
        String playerName
    ) {
        try {
            String newPlayer = teamService.addPlayer(teamName, playerName);
            return new ResponseEntity<>(newPlayer, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if (e.getMessage().contains("already exists")) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping(value = "/")
    @Operation(summary = "Delete a player")
    @ApiResponse(responseCode = "204",
                description = "Player deleted successfully")
    @ApiResponse(responseCode = "404",
                description = "Team not found")
    @ApiResponse(responseCode = "409",
                description = "Player does not exist")
    @ApiResponse(responseCode = "400",
                description = "Captain can not be removed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> removePlayer(
        @Valid
        String teamName,
        @Valid
        String playerName
    ) {
        try {
            teamService.removePlayer(teamName, playerName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Team not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if (e.getMessage().contains("Player not found")) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Team> delateTeam(
        @Valid
        String name
    ) {
        try {
            teamService.deleteTeam(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
