package ar.uba.fi.ingsoft1.todo_template.match.matchOrganizer;

import java.util.Set;

public class MatchOrganizerDTO {
    Set<Long> availablePlayers;
    Set<Long> teamAPlayers;
    Set<Long> teamBPlayers;

    public MatchOrganizerDTO(MatchOrganizer matchOrganizer) {
        this.availablePlayers = matchOrganizer.getAvailablePlayers();
        this.teamAPlayers = matchOrganizer.getTeamAPlayers();
        this.teamBPlayers = matchOrganizer.getTeamBPlayers();
    }

    public Set<Long> getAvailablePlayers(){
        return availablePlayers;
    }

    public Set<Long> getTeamAPlayers(){
        return teamAPlayers;
    }

    public Set<Long> getTeamBPlayers(){
        return teamBPlayers;
    }
}
