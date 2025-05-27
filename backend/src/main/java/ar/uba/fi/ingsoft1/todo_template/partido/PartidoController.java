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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

// task: Elección de cancha y franja horaria ( franja horaria implementada falta enganchar con la api de cancha )
// task: Franja horaria figura como reservada y ocupada en el sistema (se deberia solicitar al cancha service la reserva de una cancha)
// task: agregar partido creado historial de reservas del admin ()
// task: actualizar listado de partidos dispo (se actualiza la db, falta crear filtro para obtener aquellos partidos abiertos y disponibles)
// task: inscripcion de dos equipos al partido (faltaria checkear que sean validos)

// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYWxlIiwiaWF0IjoxNzQ4MzAyMzE1LCJleHAiOjE3NDgzMDQxMTUsInJvbGUiOiJBRE1JTiJ9.7c3k5RCKrZjHiM4VHJbjNwb6Gr5QOnzQE2riQZXPlUw
@RestController
@RequestMapping("/partido")
@Tag(name = "Partidos")
public class PartidoController {
    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    //@GetMapping(value = "/partidoCreation", produces = "application/json")
    @Operation(summary = "Get a partido given the specified characteristic")
    @ApiResponse(responseCode = "200", description = "Partidos found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Partidos not found", content = @Content)
    @ResponseStatus(HttpStatus.OK)
    Page<PartidoDTO> getProjects(
            @Valid @ParameterObject Pageable pageable
    ) throws MethodArgumentNotValidException {
        return partidoService.getPartidos(pageable);
    }

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
        return this.partidoService.createPartido(partidoCreateDTO);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a partido by its id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Partido updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Partido not found, Invalid Partido ID or you don't have permissions to access the Partido")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<PartidoDTO> updatePartido(
            @Valid @PathVariable @Positive Long id,
            @Valid @RequestBody PartidoCreateDTO partidoCreateDTO
    ) throws MethodArgumentNotValidException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        PartidoDTO updatedPartido = partidoService.updatePartido(id,partidoCreateDTO);

        if (updatedPartido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedPartido);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Partido by its id")
    @ApiResponse(responseCode = "200", description = "Partido deleted successfully")
    @ApiResponse(responseCode = "404", description = "Partido not found, Invalid Partido ID or you don't have permissions to access the Partido")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    void deletePartido(@Valid @PathVariable @Positive Long id) throws MethodArgumentNotValidException {
        partidoService.deletePartido(id);
    }


}
