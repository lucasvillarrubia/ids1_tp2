package ar.uba.fi.ingsoft1.todo_template.partido;

import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import org.springframework.web.bind.MethodArgumentNotValidException;

//          ASI DEBERIAN ESTAR DECLARADOS LOS ATRIBUTOS
//        @NotBlank @Positive Long organizerId,
//        @NotBlank @Positive Long canchaId,
//        @NotBlank ParticipationType participationType,
//        @NotBlank FranjaHoraria franjaHoraria
public record PartidoCreateDTO(
        Long organizerId,
        Long canchaId,
        ParticipationType participationType,
        TimeRange timeRange
){

    public Partido asPartido() throws MethodArgumentNotValidException {
        return new Partido(organizerId, canchaId, participationType, timeRange);
    }

}