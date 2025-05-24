package ar.uba.fi.ingsoft1.todo_template.actors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActorCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String name
) {

//    public Actor asActor(LongFunction<Optional<Movie>> getMovie) throws ItemNotFoundException {
//        return this.asActor(null, getMovie);
//    }
//
//    public Actor asActor(Long id, LongFunction<Optional<Movie>> getMovie) throws ItemNotFoundException {
//        var movie = movieId == null
//                ? null
//                : getMovie.apply(movieId).orElseThrow(() -> new ItemNotFoundException("movie", movieId));
//        return new Actor(id, name, age, movie);
//    }

    public Actor asActor() { return this.asActor(null); }

    public Actor asActor(Long actorId) { return new Actor(actorId, this.name); }

    public String getName() {
        return name;
    }

}
