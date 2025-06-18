package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record TimeSlotDTO(
    @NotNull String date,
    @NotNull String startHour,
    @NotNull String endHour
) {
    public TimeSlot asTimeSlot() {
        return new TimeSlot(
            LocalDate.parse(date),
            LocalTime.parse(startHour),
            LocalTime.parse(endHour)
        );
    }
}
