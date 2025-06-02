package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import jakarta.persistence.*;

import java.util.HashSet;


@Entity
@DiscriminatorValue("Open")
public class Open extends ParticipationType {

    private Integer minPlayersCount;

    private Integer maxPlayersCount;

    private HashSet<Long> playerIds = new HashSet<>(); // Ver de guardar como

    public Open(){}

    public Integer getMinPlayersCount() {
        return this.minPlayersCount;
    }

    public Integer getMaxPlayersCount() {
        return this.maxPlayersCount;
    }

    public Integer getPlayerCount() { return playerIds.size(); }

    //public void addPlayer(id) {}

    //public void deletePlayer(id) {}

    public HashSet<Long> getPlayerIds() { return this.playerIds; }

}
