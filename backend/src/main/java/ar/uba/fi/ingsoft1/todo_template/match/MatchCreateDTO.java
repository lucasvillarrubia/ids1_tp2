package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeDTO;
import ar.uba.fi.ingsoft1.todo_template.reservation.Reservation;
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationDTO;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.validation.constraints.NotNull;

public record MatchCreateDTO(
        @NotNull ParticipationTypeDTO participationType,
        @NotNull ReservationCreateDTO reservation
){

    public Match asMatch(User organizer, ParticipationType participationType, Reservation reserv) {
        return new Match(organizer, participationType, reserv);
    }

    public ParticipationTypeDTO getParticipationType() {
        return this.participationType;
    }
    public ReservationCreateDTO getReservation() {
        return this.reservation;
    }

}