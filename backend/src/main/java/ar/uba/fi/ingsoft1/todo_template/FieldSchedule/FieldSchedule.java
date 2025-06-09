package ar.uba.fi.ingsoft1.todo_template.FieldSchedule;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.reservation.Reservation;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
public class FieldSchedule implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "La cancha debe tener al menos un día de la semana")
    @ElementCollection
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> days;

    private LocalTime startHour;

    private LocalTime endHour;

    @Column(name = "predef_duration", nullable = false)
    @Positive(message = "La duración predefinida debe ser un valor positivo")
    private Integer predefDuration;

    @ElementCollection
    @CollectionTable(name = "schedule_unavailable_timeslots", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "unavailable_timeslots", nullable = false)
    List<TimeSlot> unavailableTimeSlots;

    public FieldSchedule() {
        this.days = new ArrayList<>();
        this.days.add(DayOfWeek.FRIDAY); // seteo un default para que no sea null
        this.startHour = LocalTime.of(8, 0); // seteo un default para que no sea null
        this.endHour = LocalTime.of(22, 0); 
        this.predefDuration =  60; // tiempo en minutos
    }

    public FieldSchedule(List<DayOfWeek> days, String startHour, String endHour, Integer predefDuration) {
        this.days = days;
        this.predefDuration = predefDuration;
        this.unavailableTimeSlots = new ArrayList<>();
        setStartHour(startHour);
        setEndHour(endHour);
    }

    public FieldSchedule(List<DayOfWeek> days, LocalTime startHour, LocalTime endHour,
            Integer predefDuration) {
        this.days = days;
        this.startHour = startHour;
        this.endHour = endHour;
        this.predefDuration = predefDuration;
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

    public int getPredefDuration() {
        return predefDuration;
    }

    public List<TimeSlot> getUnavailableTimeSlots() {
        return unavailableTimeSlots;
    }

    public void addUnavailableTimeSlot(TimeSlot timeSlot) {
        this.unavailableTimeSlots.add(timeSlot);
    }

    public List<TimeSlot> getTimeSlotsForDate(LocalDate date, List<Reservation> reservations) {
        DayOfWeek day = date.getDayOfWeek();
        if (!days.contains(day)) {
            return new ArrayList<>();
        }

        List<TimeSlot> timeSlots = new ArrayList<>();
        LocalTime actualTimeSlotStart = this.startHour;
        
        while (actualTimeSlotStart.plusMinutes(this.predefDuration).compareTo(this.endHour) <= 0) {
            LocalTime nextTimeSlotStart = actualTimeSlotStart.plusMinutes(this.predefDuration);
            TimeSlot timeSlot = new TimeSlot(date, actualTimeSlotStart, nextTimeSlotStart);

            timeSlots.add(timeSlot);
            actualTimeSlotStart = nextTimeSlotStart;
        }

        System.out.println("Reserved time slots for " + date + ": " + reservations.stream().map(res -> res.getStart() + " - " + res.getEnd()).toList());

        timeSlots.removeIf(slot -> reservations.stream()
            .anyMatch(reservation -> 
                slot.getStartHour().isBefore(reservation.getEnd()) &&
                slot.getEndHour().isAfter(reservation.getStart())
            )
        );

        timeSlots.removeIf(slot -> 
            unavailableTimeSlots.stream()
                .anyMatch(unavailable -> 
                    slot.getDate().equals(unavailable.getDate()) &&
                    slot.getStartHour().isBefore(unavailable.getEndHour()) &&
                    slot.getEndHour().isAfter(unavailable.getStartHour())
                )
        );

        return timeSlots;
    }

    public Long getId() {
        return id;
    }

    public void setDays(List<DayOfWeek> days) {
        this.days = days;
    }

    public void setStartHour(String startHour) {
        String[] startHourParts = startHour.split(":");
        this.startHour = LocalTime.of(Integer.parseInt(startHourParts[0]), Integer.parseInt(startHourParts[1]));
    }
    
    public void setEndHour(String endHour) {
        String[] endHourParts = endHour.split(":");
        this.endHour = LocalTime.of(Integer.parseInt(endHourParts[0]), Integer.parseInt(endHourParts[1]));
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }
    
    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public void setPredefDuration(Integer predefDuration) {
        this.predefDuration = predefDuration;
    }
}
