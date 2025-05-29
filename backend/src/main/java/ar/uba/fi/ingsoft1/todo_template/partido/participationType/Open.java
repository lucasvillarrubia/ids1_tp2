package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("Open")
public class Open extends ParticipationType {

    private Integer minPlayersCount;

    private Integer maxPlayersCount;

    public Open(){}

    public Integer getMinPlayersCount() {
        return this.minPlayersCount;
    }

    public Integer getMaxPlayersCount() {
        return this.maxPlayersCount;
    }

}
