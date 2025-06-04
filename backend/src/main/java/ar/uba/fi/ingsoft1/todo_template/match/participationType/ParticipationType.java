package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Open.class, name = "Open"),
        @JsonSubTypes.Type(value = Close.class, name = "Close")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class ParticipationType implements Serializable {
    @Id
    @GeneratedValue
    private Long PartTypeId;

    public String toString() {
        return null;
    }
}


