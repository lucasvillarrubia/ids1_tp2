package ar.uba.fi.ingsoft1.todo_template.partido;

import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.MethodArgumentNotValidException;

public record PartidoCreateDTO(
        @Positive Long organizerId,
        @Positive Long canchaId,
        @NotNull ParticipationType participationType,
        @NotNull TimeRange timeRange
){

    public Partido asPartido() throws MethodArgumentNotValidException {
        return new Partido(organizerId, canchaId, participationType, timeRange);
    }

    public TimeRange getTimeRange() {
        return this.timeRange;
    }
}