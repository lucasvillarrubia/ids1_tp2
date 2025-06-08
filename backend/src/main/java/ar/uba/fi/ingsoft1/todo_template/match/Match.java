package ar.uba.fi.ingsoft1.todo_template.match;


import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
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

    public void start(){
        if (!Objects.equals(this.state, "Closed")){
            throw new IllegalStateException("Cannot start match yet");
        }
        this.state = "Started";
    }

    public void close() {
        if (!Objects.equals(this.state, "Active")){
            throw new IllegalStateException("Cannot close match");
        }
        checkStart();
        this.state = "Closed";}

    public boolean leaveMatch(User user) {
        if (!Objects.equals(this.state, "Active")){
            throw new IllegalStateException("Cannot leave match");
        }
        return this.participationType.leaveMatch(user);
    }

    public boolean join(User user) {
        if (!Objects.equals(this.state, "Active")){
            throw new IllegalStateException("Cannot join match");
        }
        return participationType.addPlayer(user);
    }
    
    private void checkStart(){
        participationType.checkStart();
    }

    public void setParticipationType(ParticipationType partType) {
        this.participationType = partType;
    }
}
