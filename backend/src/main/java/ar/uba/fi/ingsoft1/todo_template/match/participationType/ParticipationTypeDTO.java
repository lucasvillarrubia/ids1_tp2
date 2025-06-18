package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OpenDTO.class, name = "Open"),
        @JsonSubTypes.Type(value = CloseDTO.class, name = "Close")
})
public abstract class ParticipationTypeDTO implements Serializable {
}