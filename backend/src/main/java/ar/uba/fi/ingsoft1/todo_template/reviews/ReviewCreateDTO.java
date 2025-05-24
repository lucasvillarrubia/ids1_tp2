
public record ReviewCreateDTO(
        String comment,
        @NotBlank @Min(1) @Max(10) Integer rating,
        @NotBlank Long fieldId
) {
    public Review asReview(Long id, LongFunction<Optional<Field>> getField) throws ItemNotFoundException {
        var field = fieldId == null
                ? null
                : getField.apply(fieldId).orElseThrow(() -> new ItemNotFoundException("field", fieldId));
        return new Review(id, comment, rating, field);
    }
}
