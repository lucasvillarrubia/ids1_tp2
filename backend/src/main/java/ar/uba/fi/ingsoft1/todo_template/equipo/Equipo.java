package ar.uba.fi.ingsoft1.todo_template.equipo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public class Equipo {
    private String nombre;
    private String capitan;
    private BufferedImage logo;
    private List<Color> colores;
    private Integer nivel;

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

    public BufferedImage getLogo() {
        return logo;
    }

    public List<Color> getColores() {
        return colores;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

    public void setLogo(BufferedImage logo) {
        this.logo = logo;
    }

    public void setColores(List<Color> colores) {
        this.colores = colores;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}