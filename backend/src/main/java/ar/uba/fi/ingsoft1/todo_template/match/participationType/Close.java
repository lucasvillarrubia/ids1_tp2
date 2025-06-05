package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.equipo.Equipo;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Close extends ParticipationType{

    @ManyToOne
    @JoinColumn(name = "TeamA", nullable = false)
    private Equipo teama;

    @ManyToOne
    @JoinColumn(name = "TeamB", nullable = false)
    private Equipo teamb;

    public Close(){}

    public Close(@NotBlank Equipo teama, @NotBlank Equipo teamb) {
        this.teama = teama;
        this.teamb = teamb;
    }

    public Equipo getTeama() {
        return teama;
    }

    public Equipo getTeamb() {
        return teamb;
    }

    @Override
    public String toString() {
        return "type: 'Close', teama: " + teama.getNombre() + "teamb: "+ teamb.getNombre();
    }

    @Override
    public boolean leaveMatch(User user) {
        return false;
    }

    @Override
    public boolean addPlayer(User user){
        return false;
    }
}
