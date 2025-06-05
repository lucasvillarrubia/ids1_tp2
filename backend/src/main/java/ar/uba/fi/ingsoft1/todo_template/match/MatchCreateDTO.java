package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeDTO;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;

public record MatchCreateDTO(
        @Positive Long fieldId,
        @NotNull ParticipationTypeDTO participationType,
        @NotNull TimeRange timeRange
){

    public Match asMatch(User organizer, Field field, ParticipationType participationType) {
        System.out.println("mail del creador: " + organizer.email());
        return new Match(organizer, field, participationType, timeRange);
    }

    public Long getFieldId() {
        return this.fieldId;
    }
    public ParticipationTypeDTO getParticipationType() {
        return this.participationType;
    }

}