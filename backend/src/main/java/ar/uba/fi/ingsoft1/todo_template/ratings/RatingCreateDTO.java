package ar.uba.fi.ingsoft1.todo_template.ratings;

import ar.uba.fi.ingsoft1.todo_template.movies.Movie;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.validation.constraints.*;

public record RatingCreateDTO(
        @NotNull @Min(1) @Max(10) Short score,
        @NotNull Long movieId,
        @NotBlank String userName
) {

    public Rating asRating() {
        return this.asRating(null, null, null);
    }

    public Rating asRating(Long id, Movie movie, User user) {
        return new Rating(id, this.score, movie, user);
    }

}

