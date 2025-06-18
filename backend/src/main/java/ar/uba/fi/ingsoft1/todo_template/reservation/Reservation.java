package ar.uba.fi.ingsoft1.todo_template.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Field field;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer", nullable = false)
    private User organizer;

    private LocalDate date;

    private LocalTime startHour;

    private LocalTime endHour;

    public Reservation() {}

    public Reservation(Field field, LocalDate date, LocalTime startHour, LocalTime endHour, User organizer) {
        if (date.isBefore(LocalDate.now()) || (date.isEqual(LocalDate.now()) && startHour.isBefore(LocalTime.now()))) {
            throw new IllegalArgumentException("Cannot reserve a field for a past date");
        }

        if (startHour.isAfter(endHour)) {
            throw new IllegalArgumentException("Start hour must be before end hour");
        }

        this.organizer = organizer;
        this.field = field;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Long getId() {
        return id;
    }

    public Field getField() {
        return field;
    }

    public User getOrganizer() {
        return organizer;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return startHour;
    }

    public LocalTime getEnd() {
        return endHour;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStart(LocalTime start) {
        this.startHour = start;
    }

    public void setEnd(LocalTime end) {
        this.endHour = end;
    }
}
