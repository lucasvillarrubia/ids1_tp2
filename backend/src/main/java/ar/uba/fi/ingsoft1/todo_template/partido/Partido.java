package ar.uba.fi.ingsoft1.todo_template.partido;


import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import jakarta.persistence.*;


@Entity
public class Partido {
    @Id
    @GeneratedValue
    private Long PartidoId;

    @Column(nullable = false)
    private Long organizerId;

    @Column(nullable = false)
    private Long canchaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participationTypeId", nullable = false)
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

    protected void setId(Long id){this.PartidoId = id;}

    public Long getId() {
        return PartidoId;
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
