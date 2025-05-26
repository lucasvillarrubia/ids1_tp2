package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.util.ArrayList;
public record FieldScheduleCreateDTO(
        ArrayList<String> days,
        Integer startHour,
        Integer endHour,
        Integer predefDuration,
        ArrayList<String> availableFieldSchedules
) {
    public FieldSchedule asFieldSchedule(Long id) {
        return new FieldSchedule(days, startHour, endHour, predefDuration, availableFieldSchedules);
    }
}