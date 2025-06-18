package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;
import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldScheduleDTO;
import ar.uba.fi.ingsoft1.todo_template.reservation.Reservation;
import ar.uba.fi.ingsoft1.todo_template.user.UserZones;

public class FieldDTO {
    private Long id;
    private String ownerEmail;
    private String name;
    private String description;
    private String location;
    private UserZones zone;
    private Integer price;
    private List<String> features;
    private FieldScheduleDTO schedule;
    private List<Long> reviews;
    private List<Long> reservations;
    private List<String> images;

    public FieldDTO(Field field) {
        this.id = field.getId();
        this.ownerEmail = field.getOwner().getEmail();
        this.name = field.getName();
        this.description = field.getDescription();
        this.location = field.getLocation();
        this.zone = field.getZone();
        this.price = field.getPrice() != null ? field.getPrice().intValue() : null;
        this.features = field.getFeatures()
                .stream()
                .map(f -> f.toString())
                .toList();
        this.schedule = new FieldScheduleDTO(field.getFieldSchedule());
        this.reviews = new ArrayList<>(field.getReviews());
        this.reservations = new ArrayList<>(field.getReservations().stream().map(Reservation::getId).toList());
        this.images = new ArrayList<>(field.getImages());
    }

    public Long getId() {
        return id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
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

    public UserZones getZone() {
        return zone;
    }

    public Integer getPrice() {
        return price;
    }

    public List<String> getFeatures() {
        return features;
    }

    public FieldScheduleDTO getSchedule() {
        return schedule;
    }

    public List<Long> getReviews() {
        return reviews;
    }

    public List<Long> getReservations() {
        return reservations;
    }

    public List<String> getImages() {
        return images;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setZone(UserZones zone) {
        this.zone = zone;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public void setSchedule(FieldScheduleDTO schedule) {
        this.schedule = schedule;
    }

    public void setReviews(List<Long> reviews) {
        this.reviews = reviews;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
