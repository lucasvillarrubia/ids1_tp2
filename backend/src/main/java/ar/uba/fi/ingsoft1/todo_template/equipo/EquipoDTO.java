package ar.uba.fi.ingsoft1.todo_template.equipo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public record EquipoDTO(
    String nombre,
    String capitan,
    BufferedImage logo,
    List<Color> colores,
    Integer nivel
) {
    public EquipoDTO(Equipo equipo) {
        this(equipo.getNombre(), equipo.getCapitan(), equipo.getLogo(), equipo.getColores(), equipo.getNivel());
    }
}
