package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeDTO;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeDTOFactory;

public record MatchDTO(
    Long id,
    String organizer,
    Long fieldId,
    ParticipationTypeDTO participationTypeDTO,
    TimeRange timeRange,
    String state
)
{

    public MatchDTO(Match match)  {
        this(match.getId(), match.getOrganizer().email(), match.getField().getId(), ParticipationTypeDTOFactory.createParticipationTypeDTO(match.getParticipationType()), match.getTimeRange(), match.getState());
    }

    public Long getId() {
        return id;
    }

    public String getOrganizer() {
        return organizer;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public ParticipationTypeDTO getParticipationType() {
        return participationTypeDTO;
    }

    public TimeRange getTimeRange(){
        return timeRange;
    }

    public String getState() { return state; }


}
