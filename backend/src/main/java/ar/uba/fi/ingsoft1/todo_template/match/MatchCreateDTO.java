package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;

public record MatchCreateDTO(
        @Positive Long organizerId,
        @Positive Long canchaId,
        @NotNull ParticipationType participationType,
        @NotNull TimeRange timeRange
){

    public Match asMatch() throws MethodArgumentNotValidException {
        return new Match(organizerId, canchaId, participationType, timeRange);
    }

    public TimeRange getTimeRange() {
        return this.timeRange;
    }
}