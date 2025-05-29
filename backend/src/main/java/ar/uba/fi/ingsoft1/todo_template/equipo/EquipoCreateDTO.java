package ar.uba.fi.ingsoft1.todo_template.equipo;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EquipoCreateDTO(
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Schema(description = "El nombre del equipo es obligatorio",
            minLength = 1,
            maxLength = 100,
            example = "All Stars",
            required = true)
    String nombre,

    @NotBlank(message = "El capitan del equipo es obligatorio")
    @Schema(description = "El capitan del equipo es obligatorio",
            minLength = 1,
            maxLength = 100,
            example = "Messi",
            required = true)
    String capitan,

    @Schema(description = "El logo del equipo es opcional",
            minLength = 1,
            maxLength = 100,
            example = "logo.png",
            required = false)
    String logo,

    @Schema(description = "Los colores del equipo son opcionales",
            example = "rojo, azul, verde",
            required = false)
    String colores,

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "El nivel del equipo debe ser al menos 1")
    @Max(value = 10, message = "El nivel del equipo no puede ser mayor a 10")
    @Schema(description = "El nivel del equipo es opcional",
            minLength = 1,
            maxLength = 10,
            example = "10",
            required = false)
    Integer nivel
) {
    public Equipo asEquipo() {
        /*Equipo equipo = new Equipo(nombre, capitan);
        
        if (logo != null) {
            equipo.setLogo(logo);
        }

        if (colores != null) {            
            equipo.setColores(colores);
        }

        if (nivel != null) {
            equipo.setNivel(nivel);
        }
        
        return equipo;*/
        return new Equipo(nombre, capitan);
    }
}