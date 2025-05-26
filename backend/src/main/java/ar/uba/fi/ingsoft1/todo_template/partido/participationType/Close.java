package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import ar.uba.fi.ingsoft1.todo_template.partido.Cancha;
import ar.uba.fi.ingsoft1.todo_template.partido.FranjaHoraria;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Close {

    @Column(nullable = false)
    Team teamA;

    @Column(nullable = false)
    Team teamB;

}
