package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeSlotDTO(
    String date,
    String startHour,
    String endHour
) {
    public TimeSlot asTimeSlot() {
        return new TimeSlot(
            LocalDate.parse(date),
            LocalTime.parse(startHour),
            LocalTime.parse(endHour)
        );
    }
}
