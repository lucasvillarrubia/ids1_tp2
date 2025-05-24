package ar.uba.fi.ingsoft1.todo_template.reviews;

public record ReviewDTO(
        long id,
        Short score,
        long fieldId,
        String username
) {
    public ReviewDTO(Review review) {
        this(review.getId(), review.getScore(), review.getField().getId(), review.getUser().getUsername());
    }
}

