package ar.uba.fi.ingsoft1.todo_template.actors;

import jakarta.persistence.*;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Actor() {}

    public Actor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
