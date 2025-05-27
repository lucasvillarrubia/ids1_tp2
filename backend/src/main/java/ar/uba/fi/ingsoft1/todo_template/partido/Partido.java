package ar.uba.fi.ingsoft1.todo_template.partido;


import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Partido {
    @Id
    @GeneratedValue
    private Long id;

    //cambiar por ID
    @Column(nullable = false)
    private String organizer;

    //cambiar por ID
    @Column(nullable = false)
    private Cancha cancha;

    @Column(nullable = false)
    private ParticipationType participationType;

    @Column(nullable = false)
    private TimeRange timeRange;




    public Partido(String organizer, Cancha cancha, ParticipationType pt, TimeRange fh) {
        this.organizer = organizer;
        this.cancha = cancha;
        this.participationType = pt;
        this.timeRange = fh;
    }

    public Partido(PartidoDTO dto){
        this.organizer = dto.getOrganizer();
        this.cancha = dto.getCancha();
        this.participationType = dto.getParticipationType();
        this.timeRange = dto.getFranjaHoraria();
    }

    public boolean esOrganizador(String currentUsername){
        return currentUsername.equals(this.organizer);
    }

    public Long getId() {
        return id;
    }

    public String getOrganizer() {
        return organizer;
    }


    public Cancha getCancha() {
        return cancha;
    }

    public ParticipationType getParticipationType() {
        return participationType;
    }

    public TimeRange getFranjaHoraria() {
        return timeRange;
    }

}
