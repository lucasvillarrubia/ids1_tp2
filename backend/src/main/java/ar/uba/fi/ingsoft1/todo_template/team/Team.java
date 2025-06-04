package ar.uba.fi.ingsoft1.todo_template.team;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "teams")
public class Team {
    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String captain;
    
    @Column(nullable = true)
    private String logo;

    @Column(nullable = true)
    private String colors;

    @Column(nullable = true)
    private Integer skill;

    @ElementCollection
    private List<String> players;

    public Team() {}

    public Team(String name, String captain) {
        this.name = name;
        this.captain = captain;
        this.logo = null;
        this.colors = null;
        this.skill = null;
        this.players = new ArrayList<>();
        this.players.add(captain);
    }

    public String getName() {
        return name;
    }

    public String getCaptain() {
        return captain;
    }

    public String getLogo() {
        return logo;
    }

    public String getColors() {
        return colors;
    }

    public Integer getSkill() {
        return skill;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setColors(String colores) {
        this.colors = colores;
    }

    public void setSkill(Integer nivel) {
        this.skill = nivel;
    }

    public boolean addPlayer(String player) {
        if (!players.contains(player)) {
            players.add(player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(String player) {
        if (players.contains(player)) {
            players.remove(player);
            return true;
        }
        return false;
    }

    public boolean isComplete() {
        return players.size() >= 5;
    }
}