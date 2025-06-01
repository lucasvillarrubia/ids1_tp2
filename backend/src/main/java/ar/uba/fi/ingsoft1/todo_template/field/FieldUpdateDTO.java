package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

public record FieldUpdateDTO(
    String name,
    String description,
    String location,
    String zone,
    Integer price,
    List<String> features,
    List<Long> reviews,
    List<Long> reservations,
    List<String> images
) {
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

    public List<String> getFeatures() {
        return features;
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
}
