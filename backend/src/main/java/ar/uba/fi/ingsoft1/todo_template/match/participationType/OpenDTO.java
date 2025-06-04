package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.match.Match;
import ar.uba.fi.ingsoft1.todo_template.match.TimeRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Set;

@DiscriminatorValue("Open")
public class OpenDTO extends ParticipationTypeDTO {
        @Positive @NotNull private Integer minPlayersCount;
        @Positive @NotNull private Integer maxPlayersCount;
        private Set<String> players; // Ver de guardar como

    public Integer getMaxPlayersCount() {
        return maxPlayersCount;
    }

    public Integer getMinPlayersCount() {
        return minPlayersCount;
    }

    public
    Set<String> getPlayers() {
        return players;
    }

    @JsonCreator
    public OpenDTO(
            @JsonProperty("minPlayersCount") Integer minPlayersCount,
            @JsonProperty("maxPlayersCount") Integer maxPlayersCount,
            @JsonProperty("players") Set<String> players
    ) {
        System.out.println("Constructed OpenDTO with: " + minPlayersCount + " / " + maxPlayersCount);

        this.minPlayersCount = minPlayersCount;
        this.maxPlayersCount = maxPlayersCount;
        this.players = players;
    }
}

