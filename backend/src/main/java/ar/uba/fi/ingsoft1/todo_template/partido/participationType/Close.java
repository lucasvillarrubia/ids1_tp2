package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import jakarta.persistence.Column;

public class Close implements ParticipationType{

    @Column(nullable = false)
    Team teamA;

    @Column(nullable = false)
    Team teamB;

}
