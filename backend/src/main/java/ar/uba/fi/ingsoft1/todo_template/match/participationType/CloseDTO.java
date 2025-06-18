package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.NotBlank;

import java.util.stream.Collectors;

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


    public CloseDTO(Close close) {
        this.teama = close.getTeama().getName();
        this.teamb = close.getTeamb().getName();
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