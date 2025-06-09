package ar.uba.fi.ingsoft1.todo_template.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.user.User;

public record ReservationCreateDTO(
    Long fieldId,
    LocalDate date,
    LocalTime start,
    LocalTime end
) {
    public Reservation asReservation(Field field, User organizer) {
        return new Reservation(
            field, 
            date,
            start,
            end,
            organizer
        );
    }

    public Long getFieldId() {
        return fieldId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}