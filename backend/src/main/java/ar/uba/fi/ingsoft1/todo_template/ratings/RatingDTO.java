package ar.uba.fi.ingsoft1.todo_template.ratings;

import ar.uba.fi.ingsoft1.todo_template.movies.Movie;
import ar.uba.fi.ingsoft1.todo_template.user.User;

public record RatingDTO(
        long id,
        Short score,
        long movieId,
        String username
) {
    public RatingDTO(Rating rating) {
        this(rating.getId(), rating.getScore(), rating.getMovie().getId(), rating.getUser().getUsername());
    }
}

