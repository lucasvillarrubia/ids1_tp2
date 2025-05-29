package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.util.ArrayList;
import java.util.List;

public record FieldScheduleDTO(
        Long id,
        List<String> days,
        Integer startHour,
        Integer endHour,
        Integer predefDuration,
        List<String> availableFieldSchedules
) {
    public FieldScheduleDTO(FieldSchedule fieldSchedule) {
        this(fieldSchedule.getId(), fieldSchedule.getDays(), fieldSchedule.getStartHour(), fieldSchedule.getEndHour(), fieldSchedule.getPredefDuration(), fieldSchedule.getAvailableFieldSchedules());
    }
}
