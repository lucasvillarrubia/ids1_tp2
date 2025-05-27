package ar.uba.fi.ingsoft1.todo_template.partido;

import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;

public record PartidoDTO (
    Long id,
    String organizer,
    Cancha cancha,
    ParticipationType participationType,
    TimeRange timeRange
)
{

    public PartidoDTO(Partido partido)  {
        this(partido.getId(), partido.getOrganizer(), partido.getCancha(), partido.getParticipationType() , partido.getFranjaHoraria());
    }

    public Long getId() {
        return id;
    }

    public String getOrganizer() {
        return organizer;
    }


    public Cancha getCancha() {
        return cancha;
    }

    public ParticipationType getParticipationType() {
        return participationType;
    }

    public TimeRange getFranjaHoraria(){
        return timeRange;
    }


}
