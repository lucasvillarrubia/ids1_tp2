package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.match.Match;
import ar.uba.fi.ingsoft1.todo_template.match.TimeRange;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Set;
import java.util.stream.Collectors;


@DiscriminatorValue("Open")
public class OpenDTO extends ParticipationTypeDTO {
        @Positive @NotNull private Integer minPlayersCount;
        @Positive @NotNull private Integer maxPlayersCount;
        private Set<String> players;

    public Integer getMaxPlayersCount() {
        return maxPlayersCount;
    }

    public Integer getMinPlayersCount() {
        return minPlayersCount;
    }

    public Set<String> getPlayers() {
        return players;
    }

    @JsonCreator
    public OpenDTO(
            @JsonProperty("minPlayersCount") Integer minPlayersCount,
            @JsonProperty("maxPlayersCount") Integer maxPlayersCount,
            @JsonProperty("players") Set<String> players
    ) {
        this.minPlayersCount = minPlayersCount;
        this.maxPlayersCount = maxPlayersCount;
        this.players = players;
    }

    public OpenDTO(Open open) {
        this.minPlayersCount = open.getMinPlayersCount();
        this.maxPlayersCount = open.getMaxPlayersCount();
        this.players = open.getPlayers().stream().map(User::getEmail).collect(Collectors.toSet());;
    }
}

