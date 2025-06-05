package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Open extends ParticipationType {

    private Integer minPlayersCount;

    private Integer maxPlayersCount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "open_players",
            joinColumns = @JoinColumn(name = "open_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> players;

    public Open(){}

    public Open(@Positive @NotNull Integer minPlayersCount, @Positive @NotNull Integer maxPlayersCount, HashSet<User> players) {
        this.maxPlayersCount = maxPlayersCount;
        this.minPlayersCount = minPlayersCount;
        this.players = players != null ? players : new HashSet<>();
    }

    public Integer getMinPlayersCount() {
        return this.minPlayersCount;
    }

    public Integer getMaxPlayersCount() {
        return this.maxPlayersCount;
    }

    public Integer getPlayerCount() { return players.size(); }

    //public void deletePlayer(id) {}

    public Set<User> getPlayers() { return this.players; }

    public void setPlayers(Set<User> players) { this.players = players; }

    @Override
    public String toString() {
        return "type: 'Open' minPlayersCount: " + minPlayersCount + "maxPlayersCount: "+ maxPlayersCount;
    }

    @Override
    public boolean addPlayer(User user) {
        if (players.size() == maxPlayersCount) {
            return false;
        }
        players.add(user);
        return true;
    }

    @Override
    public boolean leaveMatch(User user) {
        if (!players.contains(user)) {
            return false;
        }
        players.remove(user);
        return true;
    }



}
