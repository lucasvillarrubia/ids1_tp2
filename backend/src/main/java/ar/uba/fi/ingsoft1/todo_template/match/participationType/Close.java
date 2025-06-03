package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.equipo.Equipo;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Close")
public class Close extends ParticipationType{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TeamA", nullable = false)
    private Equipo teamAId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TeamB", nullable = false)
    private Equipo teamBId;

    public Close(){}

    public Equipo getTeamAId() {
        return teamAId;
    }

    public Equipo getTeamBId() {
        return teamBId;
    }

}
