package ar.uba.fi.ingsoft1.todo_template.match;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

// task: Elección de cancha y franja horaria ( franja horaria implementada falta enganchar con la api de cancha )
// task: Franja horaria figura como reservada y ocupada en el sistema (se deberia solicitar al cancha service la reserva de una cancha)
// task: agregar Match creado historial de reservas del admin
// task: actualizar listado de Matchs dispo (se actualiza la db, falta crear filtro para obtener aquellos Matchs abiertos y disponibles)
// task: inscripcion de dos equipos al Match (faltaria checkear que sean validos)

// {
//  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9iYW5kb0BleGFtcGxlLmNvbSIsImlhdCI6MTc0ODk3MjA0NCwiZXhwIjoxNzQ4OTczODQ0LCJyb2xlIjoiVVNFUiJ9.sgNyCchSAD9Y1zRj46eJsZTOQW9Npx2m72siMqS9__k",
//  "refreshToken": "6mrqf977bat3aitfc3i6ppigfhs33sal"
//}
//

@RestController
@RequestMapping("/Match")
@Tag(name = "Matches")
public class MatchRestController {
    private final MatchService matchService;

    public MatchRestController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(value = "/Match", produces = "application/json")
    @Operation(summary = "Get a Match given the specified Id")
    @ApiResponse(responseCode = "200", description = "Matchs found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Matchs not found", content = @Content)
    @ResponseStatus(HttpStatus.OK)
    MatchDTO getMatch(
            @Valid @Positive Long id
    ) throws MethodArgumentNotValidException {
        return matchService.getMatch(id);
    }

    @GetMapping(value = "/availableMatchs", produces = "application/json")
    @Operation(summary = "Get all available Match's")
    @ApiResponse(responseCode = "200", description = "Matchs found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Matchs not found", content = @Content)
    @ResponseStatus(HttpStatus.OK)
    Page<MatchDTO> getAllAvailableMatches(
            @Valid @ParameterObject Pageable pageable
    ) throws MethodArgumentNotValidException {
        return matchService.getAllAvailableMatches(pageable);
    }


    /*
   Open Match creation example:
{
  "organizerId": 13145,
  "fieldId": 2,
  "participationType": {
    "type": "Open",
    "minPlayersCount": 2,
    "maxPlayersCount": 6,
    "players": []
  },
  "timeRange": {
    "start": "08:00:00",
    "end": "09:00:00"
  }
}

    Close Match creation example:



{
    "ownerId": "11",
    "name": "Cancha 1",
    "description": "Cancha de futbol 5",
    "location": "Calle Falsa 123",
    "zone": "San Telmo",
    "features": ["GRASS", "LIGHTS", "RESTROOMS"]
}

*/

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Match")
    //TO DO CREAR NOTIFICACION VISUAL DE CORRECTA CREACION
    @ApiResponse(responseCode = "201", description = "Match created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    MatchDTO createMatch(
            @Valid @RequestBody MatchCreateDTO matchCreateDTO
    ) throws MethodArgumentNotValidException {
        System.out.println(matchCreateDTO);
        return this.matchService.createMatch(matchCreateDTO);
    }

    @PatchMapping(value = "/", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a Match by its id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Match updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Match not found, Invalid Match ID or you don't have permissions to access the Match")
    //@PreAuthorize("hasRole('USER')")
    //@Valid @PathVariable @Positive Long id,
    ResponseEntity<MatchDTO> updateMatch(
            @Valid @Positive Long id,
            @Valid @RequestBody MatchCreateDTO matchCreateDTO
    ) throws MethodArgumentNotValidException {
        MatchDTO updatedMatch = matchService.updateMatch(id, matchCreateDTO);
        return ResponseEntity.ok(updatedMatch);
    }

    //@DeleteMapping(value = "/{id}")
    @DeleteMapping(value = "/")
    @Operation(summary = "Delete a Match by its id")
    @ApiResponse(responseCode = "200", description = "Match deleted successfully")
    @ApiResponse(responseCode = "404", description = "Match not found, Invalid Match ID or you don't have permissions to access the Match")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('USER')")
    //void deleteMatch(@Valid @PathVariable @Positive Long id) throws MethodArgumentNotValidException {
    void deleteMatch(@Valid @Positive Long id) throws MethodArgumentNotValidException {
        matchService.deleteMatch(id);
    }

    @PostMapping(value = "/join")
    @Operation(summary = "Join a match")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Joined matched successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Could not join match")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<MatchDTO> joinMatch(
            @Valid @Positive Long id
    ) throws MethodArgumentNotValidException {
        MatchDTO updatedMatch = matchService.joinMatch(id);
        return ResponseEntity.ok(updatedMatch);
    }

    @PostMapping(value = "/leave")
    @Operation(summary = "Leave a match")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Left match successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Could not leave match")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<MatchDTO> leaveMatch(
            @Valid @Positive Long id
    ) throws MethodArgumentNotValidException {
        MatchDTO updatedMatch = matchService.leaveMatch(id);
        return ResponseEntity.ok(updatedMatch);
    }


}
