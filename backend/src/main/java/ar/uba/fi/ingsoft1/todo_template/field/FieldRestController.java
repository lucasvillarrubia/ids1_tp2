package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/fields")
@Tag(name = "Fields")
public class FieldRestController {

    private final FieldService fieldService;

    public FieldRestController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get all fields")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFields() {
        return fieldService.getAllFields().stream().toList();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a football field given the specified id")
    @ApiResponse(responseCode = "200", description = "Field found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    public FieldDTO getFieldById(@PathVariable @Positive Long id) {
        return fieldService.getFieldById(id);
    }


    /* ejemplo del body para crear una cancha
    {
        owner_id: 1,
        name: "Cancha 1",
        description: "Cancha de futbol 5",
        location: "Calle Falsa 123",
        zone: "San Telmo",
        features: ["GRASS", "LIGHTS", "RESTROOMS"]
    }
    */

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Field")
    @ApiResponse(responseCode = "201", description = "Field created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')") // esto imagino que solo los administradores de cancha pueden crearlas
    public FieldDTO createField(
            @Valid @RequestBody FieldCreateDTO fieldCreateDTO
    ) {
        System.out.println("CONTROLLER Creating Field with DTO: " + fieldCreateDTO);
        if (fieldCreateDTO.getOwnerId() == null || fieldCreateDTO.getName() == null || fieldCreateDTO.getZone() == null) {
            throw new IllegalArgumentException("Owner ID and Name are required to create a field.");
        }
        FieldDTO createdField = this.fieldService.createField(fieldCreateDTO);
        return createdField;
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a field by its id")
    @ApiResponse(responseCode = "200", description = "Field deleted successfully")
    @ApiResponse(responseCode = "404", description = "Field not found")
    //@PreAuthorize("hasRole('ADMIN')") // esto representa a un administrador de cancha no de app ?
    public ResponseEntity<Void> deleteField(@PathVariable @Positive long id) {
        fieldService.deleteField(id);
        return ResponseEntity.ok().build(); // TODO: chequear si se eliminó
    }

    // PATCH

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a field by its id")
    @ApiResponse(responseCode = "200", description = "Field updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public FieldDTO updateField(
            @PathVariable @Positive Long id,
            @Valid @RequestBody FieldUpdateDTO fieldUpdateDTO
    ) {
        return fieldService.updateField(id, fieldUpdateDTO);
    }

}