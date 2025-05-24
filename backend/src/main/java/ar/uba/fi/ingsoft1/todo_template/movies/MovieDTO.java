package ar.uba.fi.ingsoft1.todo_template.movies;

import ar.uba.fi.ingsoft1.todo_template.actors.Actor;
import ar.uba.fi.ingsoft1.todo_template.categories.Category;
import ar.uba.fi.ingsoft1.todo_template.ratings.Rating;

import java.util.LinkedList;
import java.util.List;

public record MovieDTO(
        long id,
        String title,
        List<Category> categories,
        List<Actor> actors,
        List<Rating> ratings
) {
    public MovieDTO(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getCategories(), movie.getActors(), movie.getRatings());
    }

    public List<Long> getActorIds() {
        LinkedList<Long> actors_id = new LinkedList<>();
        for (Actor actor : actors) {
            actors_id.add(actor.getId());
        }
        return actors_id;

    }

    public List<Long> getCategoriesIds() {
        LinkedList<Long> categories_id = new LinkedList<>();
        for (Category category : categories) {
            categories_id.add(category.getId());
        }
        return categories_id;

    }
}
