package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue
    Long id;
}
