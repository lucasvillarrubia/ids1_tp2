package ar.uba.fi.ingsoft1.todo_template.reservation;

import ar.uba.fi.ingsoft1.todo_template.user.UserZones;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDTO(
    Long id,
    Long fieldId,
    String fieldName,
    UserZones fieldZone,
    String organizerEmail,
    LocalDate date,
    LocalTime start,
    LocalTime end
) {
    public ReservationDTO(Reservation reservation) {
        this(
            reservation.getId(),
            reservation.getField().getId(),
            reservation.getField().getName(),
            reservation.getField().getZone(),
            reservation.getOrganizer().getEmail(),
            reservation.getDate(),
            reservation.getStart(),
            reservation.getEnd()
        );
    }

    public Long getId() {
        return id;
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
