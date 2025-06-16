package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeDTO;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeDTOFactory;
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationDTO;

public record MatchDTO(
    Long id,
    ParticipationTypeDTO participationType,
    ReservationDTO reservation,
    String state
)
{

    public MatchDTO(Match match)  {
        this(match.getId(), ParticipationTypeDTOFactory.createParticipationTypeDTO(match.getParticipationType()), new ReservationDTO(match.getReservation()), match.getState());
    }

    public Long getId() {
        return id;
    }

    public ParticipationTypeDTO getParticipationType() {
        return participationType;
    }

    public ReservationDTO getReservation(){
        return reservation;
    }

    public String getState() { return state; }


}
