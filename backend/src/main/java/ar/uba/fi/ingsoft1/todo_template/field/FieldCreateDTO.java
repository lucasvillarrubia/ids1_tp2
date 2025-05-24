public record FieldCreateDTO(
        String name,
        Long ownerId,
        String description,
        String address,
        String phoneNumber,
        String email,
        String website,
        String imageUrl,
        Long fieldScheduleId
) {
    public Field asField(Long id, LongFunction<Optional<FieldSchedule>> getFieldSchedule) throws ItemNotFoundException {
        var fieldSchedule = fieldScheduleId == null
                ? null
                : getFieldSchedule.apply(fieldScheduleId).orElseThrow(() -> new ItemNotFoundException("field schedule", fieldScheduleId));
        return new Field(id, ownerId, name, description, address, phoneNumber, email, website, imageUrl, fieldSchedule);
    }
}