package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldScheduleCreateDTO;

public record FieldUpdateDTO(
    String name,
    String description,
    String location,
    String zone,
    Integer price,
    List<String> features,
    List<String> images,
    FieldScheduleCreateDTO schedule
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

    public List<String> getImages() {
        return images;
    }
}
