package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldScheduleDTO;

public class FieldDTO {
    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    private String location;
    private String zone;
    private Integer price;
    private List<FieldFeatures> features;
    private FieldScheduleDTO schedule;
    private List<Long> reviews;
    private List<Long> reservations;
    private List<String> images;

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
        this.reviews = field.getReviews();
        this.reservations = field.getReservations();
        this.images = field.getImages();
    }

    public Field asField() {
        return new Field(this.id, this.ownerId, this.name, this.location, this.zone, this.features);
    }
}
