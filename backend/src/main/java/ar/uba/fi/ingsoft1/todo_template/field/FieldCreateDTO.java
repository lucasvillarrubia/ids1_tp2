package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FieldCreateDTO(
        @NonNull @Positive Long ownerId,
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String location,
        @NotNull @NotEmpty String zone,
        @NotNull @NotEmpty List<String> features,
        List<String> images
) {
    public Field asField() {
        return new Field(ownerId, name, location, zone, features.stream()
                .map(FieldFeatures::valueOf)
                .toList(), images);
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

    public List<String> getImages() {
        return images;
    }
}