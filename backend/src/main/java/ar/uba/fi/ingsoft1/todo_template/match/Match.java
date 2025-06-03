package ar.uba.fi.ingsoft1.todo_template.match;


import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Embedded;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long MatchId;

    @Column(nullable = false)
    private Long organizerId;

    @ManyToOne
    @JoinColumn(name = "fieldId", nullable = false)
    private Field field;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participationTypeId", nullable = false)
    private ParticipationType participationType;

    @Column
    private String state = "Active";

    @Embedded
    private TimeRange timeRange;

    public Match(Long organizer, Field field, ParticipationType pt, TimeRange fh) {
        this.organizerId = organizer;
        this.field = field;
        this.participationType = pt;
        this.timeRange = fh;
    }

    public Match(){}

    public Match(MatchDTO dto, Field field){
        this.organizerId = dto.getOrganizerId();
        this.field = field;
        this.participationType = dto.getParticipationType();
        this.timeRange = dto.getTimeRange();
        this.state = dto.getState();
    }

    public boolean esOrganizador(Long currentId){
        return currentId.equals(this.organizerId);
    }

    protected void setId(Long id){this.MatchId = id;}

    public Long getId() {
        return MatchId;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public Field getField() {
        return this.field;
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
