package ar.uba.fi.ingsoft1.todo_template.movies;

import ar.uba.fi.ingsoft1.todo_template.actors.Actor;
import ar.uba.fi.ingsoft1.todo_template.categories.Category;
import ar.uba.fi.ingsoft1.todo_template.ratings.Rating;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "name")
    private List<Category> categories;

    @OneToMany(mappedBy = "name")
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

    public Movie() {}

    public Movie(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie(Long id, String title, List<Category> categories, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.categories = categories;
        this.actors = actors;
        this.ratings = new ArrayList<Rating>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Category> getCategories() { return categories; }

    public List<Actor> getActors(){
        return actors;
    }

    public List<Rating> getRatings() { return ratings; }

}


