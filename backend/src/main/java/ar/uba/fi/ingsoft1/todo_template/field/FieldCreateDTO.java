package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;

public record FieldCreateDTO(
       Long ownerId,
       String name,
       String location,
       String zone,
       ArrayList<FieldFeatures> features
) {
    public Field asField() {
        return new Field(ownerId, name, location, zone, features);
    }
}