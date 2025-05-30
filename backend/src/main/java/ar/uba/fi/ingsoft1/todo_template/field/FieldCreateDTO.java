package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FieldCreateDTO(
        @NonNull @Positive Long ownerId,
        @NotNull String name,
        String location,
        String zone,
        List<String> features
) {
    public Field asField() {
        System.out.println("Creating Field from FieldCreateDTO: " + this);
        return new Field(ownerId, name, location, zone, features.stream()
                .map(FieldFeatures::valueOf)
                .toList());
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getZone() {
        return zone;
    }

    public List<String> getFeatures() {
        return features;
    }
}