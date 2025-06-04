package ar.uba.fi.ingsoft1.todo_template.equipo;

public record EquipoDTO(
    String nombre,
    String capitan,
    String logo,
    String colores,
    Integer nivel
) {
    public EquipoDTO(Equipo equipo) {
        this(equipo.getNombre(), equipo.getCapitan(), equipo.getLogo(), equipo.getColores(), equipo.getNivel());
    }

    public String getNombre() {
        return nombre;
    }

    public String getCapitan() {return capitan; }
}
