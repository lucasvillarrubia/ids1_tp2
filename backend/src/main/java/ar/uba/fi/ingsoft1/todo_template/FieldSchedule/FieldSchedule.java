package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FieldSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<String> days;

    private Integer startHour;

    private Integer endHour;

    private Integer predefDuration;

    private List<String> availableFieldSchedules;

    public FieldSchedule() {
        this.days = new ArrayList<>();
        this.startHour = 8; // seteo un default para que no sea null
        this.endHour = 22; 
        this.predefDuration = 1; // por el momento funciona con la unidad minima de horas, después lo paso a minutos
        this.availableFieldSchedules = new ArrayList<>();
    }

    public FieldSchedule(ArrayList<String> days, Integer startHour, Integer endHour, Integer predefDuration, ArrayList<String> availableFieldSchedules) {
        this.days = days;
        this.startHour = startHour;
        this.endHour = endHour;
        this.predefDuration = predefDuration;
        this.availableFieldSchedules = new ArrayList<>();
    }

    public List<String> getDays() {
        return days;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public List<String> getAvailableFieldSchedules() {
        return availableFieldSchedules;
    }
    
    public int getPredefDuration() {
        return predefDuration;
    }

    public ArrayList<String> getTimeSlots() {
        ArrayList<String> timeSlots = new ArrayList<>();
        for (int hour = startHour; hour < endHour; hour++) {
            String timeSlot = String.format("%02d:00-%02d:00", hour, hour + predefDuration);
            timeSlots.add(timeSlot);
        }
        return timeSlots;
    }

    public Long getId() {
        return id;
    }

    public void setDays(List<String> days2) {
        this.days = days2;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }
    
    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public void setPredefDuration(Integer predefDuration) {
        this.predefDuration = predefDuration;
    }

    public void setAvailableFieldSchedules() {
        this.availableFieldSchedules = getTimeSlots();
    }
}
