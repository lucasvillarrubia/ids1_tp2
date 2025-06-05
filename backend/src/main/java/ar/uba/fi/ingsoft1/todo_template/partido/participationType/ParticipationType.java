package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Open.class, name = "Open"),
        @JsonSubTypes.Type(value = Close.class, name = "Close")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class ParticipationType implements Serializable {
    @Id
    @GeneratedValue
    private Long PartTypeId;

    @Column(name = "TeamA", nullable = false)
    private Long teamAId;

    @Column(name = "TeamB", nullable = false)
    private Long teamBId;

    public Long getTeamAId() {
        return teamAId;
    }

    public Long getTeamBId() {
        return teamBId;
    }

}


