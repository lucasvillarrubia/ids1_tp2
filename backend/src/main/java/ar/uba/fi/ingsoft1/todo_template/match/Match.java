package ar.uba.fi.ingsoft1.todo_template.match;


import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "organizer", nullable = false)
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "fieldId", nullable = false)
    private Field field;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "participationTypeId", nullable = false)
    private ParticipationType participationType;

    @Column
    private String state = "Active";

    @Embedded
    private TimeRange timeRange;

    public Match(User organizer, Field field, ParticipationType pt, TimeRange fh) {
        this.organizer = organizer;
        this.field = field;
        this.participationType = pt;
        this.timeRange = fh;
    }

    public Match(){}

    public boolean isOrganizer(User actualUser){
        return actualUser.equals(this.organizer);
    }

    protected void setId(Long id){this.matchId = id;}

    public Long getId() {
        return matchId;
    }

    public User getOrganizer() {
        return organizer;
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

    public boolean leaveMatch(User user) {
        return this.participationType.leaveMatch(user);
    }

    public boolean join(User user) {
        return participationType.addPlayer(user);
    }

    public void setParticipationType(ParticipationType partType) {
        this.participationType = partType;
    }
}
