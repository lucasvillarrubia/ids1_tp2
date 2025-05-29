package ar.uba.fi.ingsoft1.todo_template.equipo;

import java.util.List;

public record EquipoDTO(
    String nombre,
    String capitan,
    String logo,
    List<String> colores,
    Integer nivel
) {
    public EquipoDTO(Equipo equipo) {
        this(equipo.getNombre(), equipo.getCapitan(), equipo.getLogo(), equipo.getColores(), equipo.getNivel());
    }

    public String getNombre() {
        return nombre;
    }
}
