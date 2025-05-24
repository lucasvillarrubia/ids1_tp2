package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldScheduleDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;

public record FieldDTO() {
    Long id;
    String name;
    Long ownerId;
    String description;
    String location;
    String zone;
    Integer price;
    ArrayList<FieldFeatures> features;
    FieldScheduleDTO schedule;
    ArrayList<ReviewDTO> reviews;
    ArrayList<String> reservations;
    ArrayList<String> images;

    public FieldDTO(Long id, Long ownerId, String name, String description, String zone, Integer price, ArrayList<FieldFeatures> features, ArrayList<images> images, String location, FieldScheduleDTO schedule, ArrayList<ReviewDTO> reviews, ArrayList<String> reservations) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.zone = zone;
        this.price = price;
        this.features = features;
        this.schedule = schedule;
        this.reviews = reviews;
        this.reservations = reservations;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getZone() {
        return zone;
    }

    public Integer getPrice() {
        return price;
    }

    public ArrayList<FieldFeatures> getFeatures() {
        return features;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public FieldScheduleDTO getSchedule() {
        return schedule;
    }

    public ArrayList<ReviewDTO> getReviews() {
        return reviews;
    }

    public ArrayList<String> getReservations() {
        return reservations;
    }
}
