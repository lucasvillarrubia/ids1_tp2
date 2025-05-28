package ar.uba.fi.ingsoft1.todo_template.partido;


import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Partido {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long organizerId;

    @Column(nullable = false)
    private Long canchaId;

    @Column(nullable = false)
    private ParticipationType participationType;

    @Embedded
    private TimeRange timeRange;

    public Partido(Long organizer, Long cancha, ParticipationType pt, TimeRange fh) {
        this.organizerId = organizer;
        this.canchaId = cancha;
        this.participationType = pt;
        this.timeRange = fh;
    }

    public Partido(){}

    public Partido(PartidoDTO dto){
        this.organizerId = dto.getOrganizerId();
        this.canchaId = dto.getCanchaId();
        this.participationType = dto.getParticipationType();
        this.timeRange = dto.getTimeRange();
    }

    public boolean esOrganizador(Long currentId){
        return currentId.equals(this.organizerId);
    }

    protected void setId(Long id){this.id = id;}

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

    public TimeRange getTimeRange() {
        return timeRange;
    }

}
