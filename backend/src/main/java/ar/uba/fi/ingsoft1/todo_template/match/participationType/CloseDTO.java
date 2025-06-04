package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.equipo.Equipo;
import ar.uba.fi.ingsoft1.todo_template.match.Match;
import ar.uba.fi.ingsoft1.todo_template.match.TimeRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;

@DiscriminatorValue("Close")
public class CloseDTO extends ParticipationTypeDTO {
    @NotBlank String teama;
    @NotBlank String teamb;

    public String getTeama() {
        return teama;
    }

    public String getTeamb() {
        return teamb;
    }

    @JsonCreator
    public CloseDTO(
            @JsonProperty("teama") String teama,
            @JsonProperty("teamb") String teamb
    ) {
        this.teama = teama;
        this.teamb = teamb;
    }
}