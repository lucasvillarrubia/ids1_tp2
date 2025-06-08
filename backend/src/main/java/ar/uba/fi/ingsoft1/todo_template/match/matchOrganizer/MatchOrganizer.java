package ar.uba.fi.ingsoft1.todo_template.match.matchOrganizer;

import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "matches_teams")
public class MatchOrganizer {
    @Id
    @GeneratedValue()
    private Long id;

    @Column
    private Set<Long> availablePlayers;

    @Column
    private Set<Long> teamAPlayers;

    @Column
    private Set<Long> teamBPlayers;

    public MatchOrganizer(Long id){
        this.id = id;
        this.availablePlayers = new HashSet<>();
        this.teamAPlayers = new HashSet<>();
        this.teamBPlayers = new HashSet<>();
    }

    public MatchOrganizer() {
    }

    public Set<Long> getAvailablePlayers() {return this.availablePlayers;}

    public Set<Long> getTeamAPlayers() { return this.teamAPlayers; }

    public Set<Long> getTeamBPlayers() { return this.teamBPlayers; }

    public void addPlayer(Long playerId) {
        this.availablePlayers.add(playerId);
    }
    public void removePlayer(Long playerId) {}

    public void movePlayer(Long id, Short team){
        // 0 team A, 1 AvailablePlayers, 2 teamB
        if (team == 0){
            availablePlayers.remove(id);
            teamBPlayers.remove(id);
            teamAPlayers.add(id);
        }
        if (team == 1){
            availablePlayers.add(id);
            teamAPlayers.remove(id);
            teamBPlayers.add(id);
        }
        if (team == 2){
            availablePlayers.add(id);
            teamAPlayers.remove(id);
            teamBPlayers.add(id);
        }
    }

    public void finish(){
        if (!this.availablePlayers.isEmpty()){
            throw new InvalidActionException("You need to asign all players before starting");
        }
        if (this.teamAPlayers.size() != this.teamBPlayers.size()){
            throw new InvalidActionException("Both teams need to have the same number of players");
        }
    }

    public void generateRandomTeams(){
        List<Long> listAvailablePlayers = new ArrayList<>(availablePlayers);
        Collections.shuffle(listAvailablePlayers);

        int halfSize = listAvailablePlayers.size() / 2;

        teamAPlayers.clear();
        teamBPlayers.clear();

        teamAPlayers.addAll(listAvailablePlayers.subList(0, halfSize));
        teamBPlayers.addAll(listAvailablePlayers.subList(halfSize, listAvailablePlayers.size()));
    }
}
