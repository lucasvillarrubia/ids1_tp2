package ar.uba.fi.ingsoft1.todo_template.equipo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipos")
@Tag(name = "Equipos")
public class EquipoRestController {
    private final EquipoService equipoService;

    public EquipoRestController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping(consumes = "application/json",
                produces = "application/json")
    @Operation(summary = "Crea un nuevo equipo")
    @ApiResponse(responseCode = "201",
                description = "Equipo creado exitosamente")
    @ApiResponse(responseCode = "400",
                description = "Ya existe un equipo con ese nombre")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EquipoDTO> crearEquipo(
        @Valid
        @RequestBody
        EquipoCreateDTO equipoCreateDTO,
        String capitan
    ) {
        EquipoDTO equipoDTO = equipoService.crearEquipo(equipoCreateDTO, capitan);
        
        if (equipoDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(equipoDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{nombre}",
                produces = "application/json")
    @Operation(summary = "Obtiene un equipo")
    @ApiResponse(responseCode = "201",
                description = "Equipo obtenido exitosamente")
    @ApiResponse(responseCode = "404",
                description = "No existe un equipo con ese nombre")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EquipoDTO> obtenerEquipo(
        @Valid
        String nombre
    ) {
        EquipoDTO equipoDTO = equipoService.obtenerEquipo(nombre);
        
        if (equipoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(equipoDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Obtene equipos")
    @ApiResponse(responseCode = "201",
                description = "Equipos obtenidos exitosamente")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EquipoDTO>> obtenerEquipos() {
        List<EquipoDTO> equipos = equipoService.obtenerEquipos();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PatchMapping(consumes = "application/json",
                produces = "application/json")
    @Operation(summary = "Actualiza un equipo")
    @ApiResponse(responseCode = "200",
                description = "Equipo actualizado exitosamente")
    @ApiResponse(responseCode = "400",
                description = "Ya existe un equipo con ese nombre")
    @ApiResponse(responseCode = "404",
                description = "No existe un equipo con ese nombre")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EquipoDTO> actualizarEquipo(
        @Valid
        String nombre,
        @Valid
        @RequestBody
        EquipoCreateDTO equipoCreateDTO
    ) {
        EquipoDTO equipoDTO = equipoService.actualizarEquipo(nombre, equipoCreateDTO);
        
        if (equipoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (equipoDTO.getNombre() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(equipoDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{nombre}")
    @Operation(summary = "Elimina un equipo")
    @ApiResponse(responseCode = "204",
                description = "Equipo eliminado exitosamente")
    @ApiResponse(responseCode = "404",
                description = "No existe un equipo con ese nombre")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Equipo> eliminarEquipo(
        @Valid
        String nombre
    ) {
        boolean eliminado = equipoService.eliminarEquipo(nombre);
        
        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
