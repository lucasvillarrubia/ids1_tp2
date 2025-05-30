package ar.uba.fi.ingsoft1.todo_template.reviews;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReviewCreateDTO(
    @NotNull @Positive Long field_id,
    @NotNull @Min(1) @Max(5) Short score,
    String comment
) {
    public Review asReview() {
        return new Review(score, comment);
    }
}
