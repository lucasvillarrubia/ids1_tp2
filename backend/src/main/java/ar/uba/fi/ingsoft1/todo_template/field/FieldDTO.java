package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;
import java.util.stream.Collectors;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldScheduleDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;

public class FieldDTO {
    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    private String location;
    private String zone;
    private Integer price;
    private ArrayList<FieldFeatures> features;
    private FieldScheduleDTO schedule;
    private ArrayList<ReviewDTO> reviews;
    private ArrayList<String> reservations;
    private ArrayList<String> images;

    public FieldDTO(Field field) {
        this.id = field.getId();
        this.ownerId = field.getOwnerId();
        this.name = field.getName();
        this.description = field.getDescription();
        this.location = field.getLocation();
        this.zone = field.getZone();
        this.price = field.getPrice() != null ? field.getPrice().intValue() : null;
        this.features = field.getFeatures();
        this.schedule = new FieldScheduleDTO(field.getFieldSchedule());
        this.reviews = new ArrayList<>(field.getReviews().stream().map(ReviewDTO::new).collect(Collectors.toList()));
        this.reservations = new ArrayList<>(field.getReservations().stream().map(String::valueOf).collect(Collectors.toList()));
        this.images = field.getImages();
    }

    public Field asField() {
        return new Field(this.id, this.ownerId, this.name, this.location, this.zone, this.features);
    }
}
