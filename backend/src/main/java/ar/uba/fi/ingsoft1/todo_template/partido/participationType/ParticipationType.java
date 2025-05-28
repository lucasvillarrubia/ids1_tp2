package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"  // name of the type field in JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Open.class, name = "Open"),
        @JsonSubTypes.Type(value = Close.class, name = "Close")
})
public interface ParticipationType extends Serializable {
}
