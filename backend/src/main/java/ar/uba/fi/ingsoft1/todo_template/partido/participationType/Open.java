package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.Column;

import java.util.List;

public class Open {

    List<User> players;

    @Column(nullable = false)
    Integer minPlayersCount;

    @Column(nullable = false)
    Integer maxPlayersCount;
}
