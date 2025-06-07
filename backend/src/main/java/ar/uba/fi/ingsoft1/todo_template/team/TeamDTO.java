package ar.uba.fi.ingsoft1.todo_template.team;

import java.util.Arrays;

public record TeamDTO(
    String name,
    String captain,
    String logo,
    String colors,
    Integer skill,
    String[] players
) {
    public TeamDTO(Team team) {
        this(team.getName(),
            team.getCaptain(),
            team.getLogo(),
            team.getColors(),
            team.getSkill(),
            team.getPlayers().toArray(new String[0]));
    }

    public Team asTeam() {
        Team team = new Team(name, captain);
        team.setLogo(logo);
        team.setColors(colors);
        team.setSkill(skill);
        team.setPlayers(Arrays.asList(players));
        return team;
    }
}
