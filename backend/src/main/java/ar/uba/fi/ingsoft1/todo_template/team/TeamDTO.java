package ar.uba.fi.ingsoft1.todo_template.team;

import java.util.List;

public record TeamDTO(
    String name,
    String captain,
    String logo,
    String colors,
    Integer skill,
    List<String> players
) {
    public TeamDTO(Team team) {
        this(team.getName(),
            team.getCaptain(),
            team.getLogo(),
            team.getColors(),
            team.getSkill(),
            team.getPlayers());
    }

    public String getNombre() {
        return name;
    }
}
