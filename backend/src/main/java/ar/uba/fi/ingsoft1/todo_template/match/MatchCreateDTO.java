package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;

public record MatchCreateDTO(
        @Positive Long organizerId,
        @Positive Long fieldId,
        @NotNull ParticipationType participationType,
        @NotNull TimeRange timeRange
){

    public Match asMatch(Field field) throws MethodArgumentNotValidException {
        return new Match(organizerId, field, participationType, timeRange);
    }

    public Long getFieldId() {
        return this.fieldId;
    }
}