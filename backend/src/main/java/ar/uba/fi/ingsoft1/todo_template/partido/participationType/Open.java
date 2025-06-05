package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import jakarta.persistence.*;

import java.util.HashSet;


@Entity
@DiscriminatorValue("Open")
public class Open extends ParticipationType {

    private Integer minPlayersCount;

    private Integer maxPlayersCount;

    private Integer playerCount = 1;

    private HashSet<Long> playerIds = new HashSet<>();

    public Open(){}

    public Integer getMinPlayersCount() {
        return this.minPlayersCount;
    }

    public Integer getMaxPlayersCount() {
        return this.maxPlayersCount;
    }

    public Integer getPlayerCount() { return this.playerCount; }

    public void increasePlayerCount() { this.playerCount++; }

    public void decreasePlayerCount() { this.playerCount--; }

    public HashSet<Long> getPlayerIds() { return this.playerIds; }

}
