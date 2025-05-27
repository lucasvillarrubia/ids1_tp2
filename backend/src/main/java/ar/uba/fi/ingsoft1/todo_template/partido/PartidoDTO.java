package ar.uba.fi.ingsoft1.todo_template.partido;

import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;

public record PartidoDTO (
    Long id,
    Long organizerId,
    Long canchaId,
    ParticipationType participationType,
    TimeRange timeRange
)
{

    public PartidoDTO(Partido partido)  {
        this(partido.getId(), partido.getOrganizerId(), partido.getCanchaId(), partido.getParticipationType() , partido.getFranjaHoraria());
    }

    public Long getId() {
        return id;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public Long getCanchaId() {
        return canchaId;
    }

    public ParticipationType getParticipationType() {
        return participationType;
    }

    public TimeRange getFranjaHoraria(){
        return timeRange;
    }


}
