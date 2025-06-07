package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
public record FieldScheduleCreateDTO(
    List<DayOfWeek> days,
    LocalTime startHour,
    LocalTime endHour,
    Integer predefDuration
) {
    public FieldSchedule asFieldSchedule(Long id) {
        return new FieldSchedule(days, startHour, endHour, predefDuration);
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public Integer getPredefDuration() {
        return predefDuration;
    }
}