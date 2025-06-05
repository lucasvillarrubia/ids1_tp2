package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;

public record MatchDTO(
    Long id,
    String organizer,
    Long fieldId,
    ParticipationType participationType,
    TimeRange timeRange,
    String state
)
{

    public MatchDTO(Match match)  {
        this(match.getId(), match.getOrganizer().email(), match.getField().getId(), match.getParticipationType() , match.getTimeRange(), match.getState());
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

    public ParticipationType getParticipationType() {
        return participationType;
    }

    public TimeRange getTimeRange(){
        return timeRange;
    }

    public String getState() { return state; }


}
