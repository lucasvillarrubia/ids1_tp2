package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;

public record MatchDTO(
    Long id,
    Long organizerId,
    Long fieldId,
    ParticipationType participationType,
    TimeRange timeRange,
    String state
)
{

    public MatchDTO(Match match)  {
        this(match.getId(), match.getOrganizerId(), match.getField().getId(), match.getParticipationType() , match.getTimeRange(), match.getState());
    }

    public Long getId() {
        return id;
    }

    public Long getOrganizerId() {
        return organizerId;
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
