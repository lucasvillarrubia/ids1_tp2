package ar.uba.fi.ingsoft1.todo_template.ratings;

import ar.uba.fi.ingsoft1.todo_template.movies.Movie;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @Min(1)
    @Max(10)
    @Column(nullable = false)
    private Short score;

    @ManyToOne(optional = false)
    private Movie movie;

    @ManyToOne(optional = false)
    private User user;

    public Rating() {}

    public Rating(Long id, Short score, Movie movie, User user) {
        this.id = id;
        this.score = score;
        this.movie = movie;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Short getScore() { return score; }

    public Movie getMovie() { return movie; }

    public User getUser() { return user; }

}



