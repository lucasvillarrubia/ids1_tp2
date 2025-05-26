package ar.uba.fi.ingsoft1.todo_template.partido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partido")
@Tag(name = "Partidos")
public class PartidoController {
    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }


    @GetMapping(value = "/gameCreation", produces = "application/json")
    @Operation(summary = "Get a partido given the specified characteristic")
    @ApiResponse(responseCode = "200", description = "Partidos found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Partidos not found", content = @Content)
    public ResponseEntity<List<Partido>> getPartidos() {
        // crear filtro
        // aca deberiamos recibir un filtro y pasarle la lista de partidos
        // y que este devuelva una lista reducida

        return ResponseEntity.ok(partidoService.getAll());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Partido")
    //TO DO CREAR NOTIFICACION VISUAL DE CORRECTA CREACION
    @ApiResponse(responseCode = "201", description = "Partido created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Partido> createPartido(
            @Valid @RequestBody Partido partidoDTO
    ) {
        Partido createdPartido = this.partidoService.createPartido(partidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPartido);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a partido by its id")
    @ApiResponse(responseCode = "200", description = "Partido updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Partido not found, Invalid Partido ID or you don't have permissions to access the Partido")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Partido> updatePartido(
            @PathVariable @Positive Long id,
            @Valid @RequestBody Partido partido
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Partido updatedPartido = partidoService.updatePartido(id, currentUsername , partido);

        if (updatedPartido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedPartido);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Partido by its id")
    @ApiResponse(responseCode = "200", description = "Partido deleted successfully")
    @ApiResponse(responseCode = "404", description = "Partido not found, Invalid Partido ID or you don't have permissions to access the Partido")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deletePartido(@PathVariable @Positive Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Partido deleted = partidoService.deletePartido(id,currentUsername);

        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }


}
