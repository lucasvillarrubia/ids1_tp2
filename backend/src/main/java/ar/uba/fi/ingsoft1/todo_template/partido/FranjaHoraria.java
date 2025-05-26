package ar.uba.fi.ingsoft1.todo_template.partido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class FranjaHoraria implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
}
