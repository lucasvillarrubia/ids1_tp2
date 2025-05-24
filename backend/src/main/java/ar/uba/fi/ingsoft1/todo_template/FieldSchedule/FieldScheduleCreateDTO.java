
public record FieldScheduleCreateDTO(
        ArrayList<String> days,
        Integer startHour,
        Integer endHour,
        Integer predefDuration
        ArrayList<String> availableFieldSchedules
) {
    public FieldSchedule asFieldSchedule(Long id) {
        return new FieldSchedule(id, days, startHour, endHour, predefDuration, availableFieldSchedules);
    }
}