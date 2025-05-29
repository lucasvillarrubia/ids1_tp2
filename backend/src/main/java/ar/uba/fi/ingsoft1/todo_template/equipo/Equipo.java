package ar.uba.fi.ingsoft1.todo_template.equipo;
import jakarta.persistence.*;

@Entity(name = "equipos")
public class Equipo {
    @Id
    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String capitan;
    
    private String logo;

    private String colores;

    private Integer nivel;

    public Equipo() {}

    public Equipo(String nombre, String capitan) {
        this.nombre = nombre;
        this.capitan = capitan;
        this.logo = null;
        this.colores = null;
        this.nivel = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCapitan() {
        return capitan;
    }

    public String getLogo() {
        return logo;
    }

    public String getColores() {
        return colores;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}