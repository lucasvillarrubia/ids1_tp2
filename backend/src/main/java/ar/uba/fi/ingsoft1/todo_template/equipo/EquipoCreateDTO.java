package ar.uba.fi.ingsoft1.todo_template.equipo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipoCreateDTO(
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 1, max = 100)
    @Schema(description = "El nombre del equipo es obligatorio",
            minLength = 1,
            maxLength = 100,
            example = "All Stars",
            required = true)
    String nombre,

    @Schema(description = "El logo del equipo es opcional",
            example = "logo.png",
            required = false)
    BufferedImage logo,

    @Schema(description = "Los colores del equipo son opcionales",
            required = false)
    List<Color> colores,

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
    public Equipo asEquipo(String capitan) {
        Equipo equipo = new Equipo(this.nombre, capitan);
        
        if (this.logo != null) {
            equipo.setLogo(this.logo);
        }

        if (this.colores != null) {
            equipo.setColores(this.colores);
        }

        if (this.nivel != null) {
            equipo.setNivel(this.nivel);
        }
        
        return equipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public BufferedImage getLogo() {
        return this.logo;
    
    }
    public List<Color> getColores() {
        return this.colores;
    }

    public Integer getNivel() {
        return this.nivel;
    }
}