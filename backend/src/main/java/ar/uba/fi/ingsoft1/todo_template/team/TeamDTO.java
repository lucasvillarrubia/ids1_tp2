package ar.uba.fi.ingsoft1.todo_template.team;

public record TeamDTO(
    String name,
    String captain,
    String logo,
    String colors,
    Integer skill
) {
    public TeamDTO(Team team) {
        this(team.getName(), team.getCaptain(), team.getLogo(), team.getColors(), team.getSkill());
    }

    public String getNombre() {
        return name;
    }
}
