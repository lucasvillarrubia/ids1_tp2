package ar.uba.fi.ingsoft1.todo_template.partido;

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

import java.time.LocalTime;

// task: Elección de cancha y franja horaria ( franja horaria implementada falta enganchar con la api de cancha )
// task: Franja horaria figura como reservada y ocupada en el sistema (se deberia solicitar al cancha service la reserva de una cancha)
// task: agregar partido creado historial de reservas del admin ()
// task: actualizar listado de partidos dispo (se actualiza la db, falta crear filtro para obtener aquellos partidos abiertos y disponibles)
// task: inscripcion de dos equipos al partido (faltaria checkear que sean validos)

// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYWxlIiwiaWF0IjoxNzQ4MzAyMzE1LCJleHAiOjE3NDgzMDQxMTUsInJvbGUiOiJBRE1JTiJ9.7c3k5RCKrZjHiM4VHJbjNwb6Gr5QOnzQE2riQZXPlUw
// 4o3bjlestcbb9gb24klqpbokqun7b9hq
@RestController
@RequestMapping("/partido")
@Tag(name = "Partidos")
public class PartidoController {
    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping(value = "/partido", produces = "application/json")
    @Operation(summary = "Get a partido given the specified characteristic")
    @ApiResponse(responseCode = "200", description = "Partidos found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Partidos not found", content = @Content)
    @ResponseStatus(HttpStatus.OK)
    Page<PartidoDTO> getProjects(
            @Valid @ParameterObject Pageable pageable
    ) throws MethodArgumentNotValidException {
        return partidoService.getPartidos(pageable);
    }


    /*
   partido creation example:
{
  "organizerId": 13145,
  "canchaId": 111314,
  "participationType": {
    "type": "Open"
  },
  "timeRange": {
    "start": "07:00:00",
    "end": "09:00:00"
  }
}
    */
    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Partido")
    //TO DO CREAR NOTIFICACION VISUAL DE CORRECTA CREACION
    @ApiResponse(responseCode = "201", description = "Partido created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    PartidoDTO createPartido(
            @Valid @RequestBody PartidoCreateDTO partidoCreateDTO
    ) throws MethodArgumentNotValidException {
        //System.out.println("start: " + partidoCreateDTO.getTimeRange().start + "\nend:" + partidoCreateDTO.getTimeRange().end);
        return this.partidoService.createPartido(partidoCreateDTO);
    }

    @PatchMapping(value = "/", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a partido by its id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Partido updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Partido not found, Invalid Partido ID or you don't have permissions to access the Partido")
    //@PreAuthorize("hasRole('USER')")
    //@Valid @PathVariable @Positive Long id,
    ResponseEntity<PartidoDTO> updatePartido(
            @Valid @Positive Long id,
            @Valid @RequestBody PartidoCreateDTO partidoCreateDTO
    ) throws MethodArgumentNotValidException {
        PartidoDTO updatedPartido = partidoService.updatePartido(id,partidoCreateDTO);
        return ResponseEntity.ok(updatedPartido);
    }

    //@DeleteMapping(value = "/{id}")
    @DeleteMapping(value = "/")
    @Operation(summary = "Delete a Partido by its id")
    @ApiResponse(responseCode = "200", description = "Partido deleted successfully")
    @ApiResponse(responseCode = "404", description = "Partido not found, Invalid Partido ID or you don't have permissions to access the Partido")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('USER')")
    //void deletePartido(@Valid @PathVariable @Positive Long id) throws MethodArgumentNotValidException {
    void deletePartido(@Valid @Positive Long id) throws MethodArgumentNotValidException {
        partidoService.deletePartido(id);
    }


}
