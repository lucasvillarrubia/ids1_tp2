package ar.uba.fi.ingsoft1.todo_template.ratings;

import ar.uba.fi.ingsoft1.todo_template.actors.Actor;
import ar.uba.fi.ingsoft1.todo_template.categories.Category;
import ar.uba.fi.ingsoft1.todo_template.movies.Movie;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class RatingService {

    final private Map<Long, RatingDTO> ratings;

    public RatingService() {
        this.ratings = new HashMap<>();

        Category sciFi = new Category(1L, "Science Fiction");
        Category drama = new Category(2L, "Drama");
        Category action = new Category(3L, "Action");

        Actor matthew = new Actor(1L, "Matthew McConaughey");
        Actor anne = new Actor(2L, "Anne Hathaway");
        Actor leonardo = new Actor(5L, "Leonardo DiCaprio");
        Actor brad = new Actor(6L, "Brad Pitt");

        Movie interstellar = new Movie(
                1L,
                "Interstellar",
                Arrays.asList(sciFi, drama),
                Arrays.asList(matthew, anne)
        );

        Movie inception = new Movie(
                3L,
                "Inception",
                Arrays.asList(sciFi, action),
                Arrays.asList(leonardo, brad)
        );

        User u1 = new User(
                "jdoe",
                "password123",
                "jdoe@example.com",
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "MALE"
        );

        User u2 = new User(
                "csantos",
                "qwerty789",
                "csantos@example.com",
                "Carlos",
                "Santos",
                LocalDate.of(1987, 12, 1),
                "MALE"
        );

        Rating rating1 = new Rating(
                1L,
                (short) 5,
                inception,
                u2
        );

        Rating rating2 = new Rating(
                2L,
                (short) 9,
                interstellar,
                u1
        );

        this.ratings.put(rating1.getId(), new RatingDTO(rating1));
        this.ratings.put(rating2.getId(), new RatingDTO(rating2));
    }

    public List<RatingDTO> getRatingsByMovie(long movieId) {
        List<RatingDTO> result = new ArrayList<>();
        for (Map.Entry<Long, RatingDTO> entry : this.ratings.entrySet()) {
            if (entry.getValue().movieId() == movieId) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    RatingDTO getRating(long id) {
        return this.ratings.get(id);
    }

    public List<RatingDTO> getRatingsByUser(String username) {
        List<RatingDTO> result = new ArrayList<>();
        for (Map.Entry<Long, RatingDTO> entry : this.ratings.entrySet()) {
            if (entry.getValue().username().equals(username)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    boolean deleteRating(long id) {
        if (this.ratings.containsKey(id)) {
            this.ratings.remove(id);
            return true;
        }
        return false;
    }

    public RatingDTO createRating(RatingCreateDTO dto) {
        Category terrorCategory = new Category(3L, "Terror");
        Actor actor1 = new Actor(7L, "Finn Wolfhard");
        String title = "It";
        Short score = 7;

        List<Category> categories = new ArrayList<>();
        categories.add(terrorCategory);

        List<Actor> actors = new ArrayList<>();
        actors.add(actor1);

        Movie movie = new Movie(
                5L,
                title,
                categories,
                actors
        );

        User user = new User(
                "lrojas",
                "adminpass",
                "lrojas@example.com",
                "Lucía",
                "Rojas",
                LocalDate.of(1992, 3, 9),
                "FEMALE"
        );

        RatingDTO ratingDTO = new RatingDTO(
                3L,
                score,
                movie.getId(),
                user.getUsername()
        );
        return ratingDTO;
    }
}

