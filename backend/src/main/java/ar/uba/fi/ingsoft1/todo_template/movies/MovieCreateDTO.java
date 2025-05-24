package ar.uba.fi.ingsoft1.todo_template.movies;

import ar.uba.fi.ingsoft1.todo_template.actors.Actor;
import ar.uba.fi.ingsoft1.todo_template.categories.Category;
import ar.uba.fi.ingsoft1.todo_template.ratings.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.LinkedList;
import java.util.List;

public record MovieCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String title,
        @Size(max = 10) List<Category> categories,
        @Size(max = 20) List<Actor> actors
) {

    public Movie asMovie() {
        return this.asMovie(null);
    }

    public Movie asMovie(Long id) {
        return new Movie(id, this.title, this.categories, this.actors);
    }

    public String getTitle() {
        return title;
    }
}
