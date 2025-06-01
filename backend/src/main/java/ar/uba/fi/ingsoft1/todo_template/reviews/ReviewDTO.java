package ar.uba.fi.ingsoft1.todo_template.reviews;

public record ReviewDTO(
        long id,
        Short score,
        String comment
) {
    public ReviewDTO(Review review) {
        this(review.getId(), review.getScore(), review.getComment());
    }

    public Review asReview() {
        return new Review(id, score, comment);
    }
}

