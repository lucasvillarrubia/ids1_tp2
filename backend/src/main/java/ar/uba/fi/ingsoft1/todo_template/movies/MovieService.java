package ar.uba.fi.ingsoft1.todo_template.movies;

import ar.uba.fi.ingsoft1.todo_template.actors.Actor;
import ar.uba.fi.ingsoft1.todo_template.actors.ActorCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.categories.Category;
import ar.uba.fi.ingsoft1.todo_template.categories.CategoryCreateDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
class MovieService {

    final private Map<Long, MovieDTO> movies;

    public MovieService() {
        this.movies = new HashMap<>();

        Category sciFi = new Category(1L, "Science Fiction");
        Category drama = new Category(2L, "Drama");
        Category action = new Category(3L, "Action");
        Category adventure = new Category(4L, "Adventure");

        Actor matthew = new Actor(1L, "Matthew McConaughey");
        Actor anne = new Actor(2L, "Anne Hathaway");
        Actor keanu = new Actor(3L, "Keanu Reeves");
        Actor carrie = new Actor(4L, "Carrie-Anne Moss");
        Actor leonardo = new Actor(5L, "Leonardo DiCaprio");
        Actor brad = new Actor(6L, "Brad Pitt");

        Movie interstellar = new Movie(
                1L,
                "Interstellar",
                Arrays.asList(sciFi, drama),
                Arrays.asList(matthew, anne)
        );

        Movie theMatrix = new Movie(
                2L,
                "The Matrix",
                Arrays.asList(sciFi, action),
                Arrays.asList(keanu, carrie)
        );

        Movie inception = new Movie(
                3L,
                "Inception",
                Arrays.asList(sciFi, action),
                Arrays.asList(leonardo, brad)
        );

        Movie darkKnight = new Movie(
                4L,
                "The Dark Knight",
                Arrays.asList(action, adventure),
                Arrays.asList(keanu, carrie)
        );

        this.movies.put(interstellar.getId(), new MovieDTO(interstellar));
        this.movies.put(theMatrix.getId(), new MovieDTO(theMatrix));
        this.movies.put(inception.getId(), new MovieDTO(inception));
        this.movies.put(darkKnight.getId(), new MovieDTO(darkKnight));
    }

    List<MovieDTO> getMovieByActor(long actorId) {
        List<MovieDTO> result = new ArrayList<>();
        for (Map.Entry<Long, MovieDTO> entry : this.movies.entrySet()) {
            if (entry.getValue().getActorIds().contains(actorId)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    MovieDTO getMovie(long id) {
        return this.movies.get(id);
    }

    List<MovieDTO> getMovieByCategory(long categoryId) {
        List<MovieDTO> result = new ArrayList<>();
        for (Map.Entry<Long, MovieDTO> entry : this.movies.entrySet()) {
            if (entry.getValue().getCategoriesIds().contains(categoryId)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public MovieDTO updateMovie(long id, MovieCreateDTO movieCreateDTO) {
        if (this.movies.containsKey(id)) {
            this.movies.put(id, new MovieDTO(movieCreateDTO.asMovie(id)));
            return this.movies.get(id);
        }
        return null;
    }

    public MovieDTO replaceMovieActors(long id, List<ActorCreateDTO> actors) {
        if (!this.movies.containsKey(id)) return null;
        MovieDTO movieDTO = this.movies.get(id);
        List<Actor> newActors = new ArrayList<>();
        for (ActorCreateDTO actor : actors) {
            newActors.add(actor.asActor());
        }
        Movie editedMovie = new Movie(
                id,
                movieDTO.title(),
                movieDTO.categories(),
                newActors
        );
        return new MovieDTO(editedMovie);
    }

    public MovieDTO replaceMovieCategories(long id, List<CategoryCreateDTO> categories) {
        if (!this.movies.containsKey(id)) return null;
        MovieDTO movieDTO = this.movies.get(id);
        List<Category> newCategories = new ArrayList<>();
        for (CategoryCreateDTO cat : categories) {
            newCategories.add(cat.asCategory());
        }
        Movie editedMovie = new Movie(
                id,
                movieDTO.title(),
                newCategories,
                movieDTO.actors()
        );
        return new MovieDTO(editedMovie);
    }

    boolean deleteMovie(long id) {
        if (this.movies.containsKey(id)) {
            this.movies.remove(id);
            return true;
        }
        return false;
    }

    public MovieDTO createMovie(MovieCreateDTO dto) {
        Category actionCategory = new Category(3L, "Action");
        Category adventureCategory = new Category(4L, "Adventure");

        Actor actor1 = new Actor(7L, "Jamie Foxx");
        Actor actor2 = new Actor(8L, "Christoph Waltz");

        String title = "Django: Unchained";

        List<Category> categories = new ArrayList<>();
        categories.add(actionCategory);
        categories.add(adventureCategory);

        List<Actor> actors = new ArrayList<>();
        actors.add(actor1);
        actors.add(actor2);

        return new MovieDTO(
                5L,
                title,
                categories,
                actors,
                new ArrayList<>()
        );
    }
}
