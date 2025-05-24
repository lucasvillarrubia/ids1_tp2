package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.util.ArrayList;

public record FieldScheduleDTO(
        Long id,
        ArrayList<String> days,
        Integer startHour,
        Integer endHour,
        Integer predefDuration,
        ArrayList<String> availableFieldSchedules
) {
    public FieldScheduleDTO(FieldSchedule fieldSchedule) {
        this(fieldSchedule.getId(), fieldSchedule.getDays(), fieldSchedule.getStartHour(), fieldSchedule.getEndHour(), fieldSchedule.getPredefDuration(), fieldSchedule.getAvailableFieldSchedules());
    }
}
