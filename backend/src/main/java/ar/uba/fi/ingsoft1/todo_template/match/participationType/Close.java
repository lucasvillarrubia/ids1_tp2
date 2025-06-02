package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Close")
public class Close extends ParticipationType{
    @Column(name = "TeamA", nullable = false)
    private Long teamAId;

    @Column(name = "TeamB", nullable = false)
    private Long teamBId;

    public Close(){}

    public Long getTeamAId() {
        return teamAId;
    }

    public Long getTeamBId() {
        return teamBId;
    }

}
