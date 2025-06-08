package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.user.UserZones;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FieldCreateDTO(
        @NonNull @Positive Long ownerId,
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String location,
        @NotNull UserZones zone,
        @NotNull @NotEmpty List<FieldFeatures> features,
        List<String> images
) {
    public Field asField() {
        return new Field(ownerId, name, location, zone, features, images);
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

    public UserZones getZone() {
        return zone;
    }

    public List<FieldFeatures> getFeatures() {
        return features;
    }

    public List<String> getImages() {
        return images;
    }
}