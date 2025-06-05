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

    @Column
    private String state = "Active";

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
        this.state = dto.getState();
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

    public String getState() { return state; }

    public ParticipationType getParticipationType() {
        return participationType;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void closeMatch(){
        this.state = "Closed";
    }

    public void endMatch() {
        this.state = "Ended";
    }

    public boolean canJoin() {
        return this.state.equals("Active");
    }

    public boolean canLeave() {
        return this.canJoin();
    }

}
