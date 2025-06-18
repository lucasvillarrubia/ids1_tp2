package ar.uba.fi.ingsoft1.todo_template.reviews;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Schema(description = "Creacion de la reseña", example = """
        {"id": 123,
          "fieldId": 456,
          "score": 4,
          "comment": "¡Excelente experiencia en el campo! Lo recomiendo.",
          "createdAt": "2025-06-17T22:00:00Z"
        }
        """)

public record ReviewCreateDTO(
    @NotNull @Positive Long field_id,
    @NotNull @Min(1) @Max(5) Short score,
    String comment
) {
    public Review asReview() {
        return new Review(score, comment);
    }
}
