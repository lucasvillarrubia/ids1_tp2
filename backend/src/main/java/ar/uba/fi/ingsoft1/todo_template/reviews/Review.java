package ar.uba.fi.ingsoft1.todo_template.reviews;


@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Min(1)
    @Max(10)
    @Column(nullable = false)
    private Short score;

    @ManyToOne(optional = false)
    private Field field;

    @ManyToOne(optional = false)
    private User user;

    public Review() {}

    public Review(Long id, Short score, Field field, User user) {
        this.id = id;
        this.score = score;
        this.field = field;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Short getScore() { return score; }

    public Field getField() { return field; }

    public User getUser() { return user; }

}
