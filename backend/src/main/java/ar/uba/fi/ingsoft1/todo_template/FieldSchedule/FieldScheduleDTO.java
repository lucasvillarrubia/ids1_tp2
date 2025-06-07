package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record FieldScheduleDTO(
    Long id,
    List<String> days,
    LocalTime startHour,
    LocalTime endHour,
    Integer predefDuration
    //List<String> availableFieldSchedules
    ){

    public FieldScheduleDTO(FieldSchedule fieldSchedule) {
        this(
                fieldSchedule.getId(), 
                fieldSchedule.getDays().stream().map(DayOfWeek::toString).toList(),
                fieldSchedule.getStartHour(), 
                fieldSchedule.getEndHour(),
                fieldSchedule.getPredefDuration()
                //fieldSchedule.getAvailableTimeSlots().stream().map(timeSlot -> timeSlot.toString()).toList()
            );
    }
}
