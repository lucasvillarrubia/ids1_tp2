package ar.uba.fi.ingsoft1.todo_template.partido;

import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import org.springframework.web.bind.MethodArgumentNotValidException;

//          ASI DEBERIAN ESTAR DECLARADOS LOS ATRIBUTOS
//        @NotBlank @Positive Long organizerId,
//        @NotBlank @Positive Long canchaId,
//        @NotBlank ParticipationType participationType,
//        @NotBlank FranjaHoraria franjaHoraria
public record PartidoCreateDTO(
        String organizer,
        Cancha cancha,
        ParticipationType participationType,
        TimeRange timeRange
){
    /*
    public Partido asPartido() throws ItemNotFoundException {
        return this.asPartido(null, getProject);
    }
    */
    public Partido asPartido() throws MethodArgumentNotValidException {
        // VERIFICAR TO DO
        // VERIFICAR EXISTENCIA DE USERID,CANCHAID, EQUIPOS (EN CASO DE PARTIDO CERRADO)
        return new Partido(organizer,cancha, participationType, timeRange);
    }

}