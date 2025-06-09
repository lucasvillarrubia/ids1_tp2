package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class TimeSlot implements Serializable{
    private LocalTime startHour;
    private LocalTime endHour;

    public TimeSlot() {}

    public TimeSlot(LocalTime startHour, LocalTime endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    @Override
    public String toString() {
        return startHour + " - " + endHour;
    }
}
