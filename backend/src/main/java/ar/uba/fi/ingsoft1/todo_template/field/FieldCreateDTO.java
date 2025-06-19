package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserZones;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FieldCreateDTO(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String location,
        @NotNull @Valid UserZones zone,
        @NotNull @NotEmpty List<FieldFeatures> features,
        List<String> images
) {
    public Field asField(User owner) {
        return new Field(owner, name, location, zone, features, images);
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