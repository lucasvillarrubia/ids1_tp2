package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class TimeSlot implements Serializable{
    private LocalDate day;
    private LocalTime startHour;
    private LocalTime endHour;

    public TimeSlot() {}

    public TimeSlot(LocalDate day, LocalTime startHour, LocalTime endHour) {
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    @Override
    public String toString() {
        return day + " " + startHour + " - " + endHour;
    }
}
