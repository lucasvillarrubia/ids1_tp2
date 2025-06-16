package ar.uba.fi.ingsoft1.todo_template.field;

import static org.junit.Assert.assertTrue;

import java.util.List;

import jakarta.validation.Validator;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ar.uba.fi.ingsoft1.todo_template.user.UserZones;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

public class TestFieldCreateDTO {
    private Validator validator;

    private FieldCreateDTO buildValidDTO() {
        return new FieldCreateDTO(
                "Cancha de Prueba",
                "Calle 123",
                UserZones.AVELLANEDA,
                List.of(FieldFeatures.GRASS,
                        FieldFeatures.WIFI,
                        FieldFeatures.PARKING),
                List.of(
                        "https://example.com/image1.jpg",
                        "https://example.com/image2.jpg"));
    }

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidFieldCreateDTO() {
        FieldCreateDTO dto = buildValidDTO();
        Set<ConstraintViolation<FieldCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testBlankNameFailsValidation() {
        FieldCreateDTO dto = new FieldCreateDTO(
                "", "Calle 123", UserZones.AVELLANEDA,
                List.of(FieldFeatures.GRASS), List.of("https://example.com/image1.jpg"));

        Set<ConstraintViolation<FieldCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void testNullZoneFailsValidation() {
        FieldCreateDTO dto = new FieldCreateDTO(
                "Cancha de Prueba", "Calle 123", null,
                List.of(FieldFeatures.GRASS), List.of("https://example.com/image1.jpg"));

        Set<ConstraintViolation<FieldCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("zone")));
    }

    @Test
    public void testEmptyFeaturesFailsValidation() {
        FieldCreateDTO dto = new FieldCreateDTO(
                "Cancha de Prueba", "Calle 123", UserZones.AVELLANEDA,
                List.of(), List.of("https://example.com/image1.jpg"));

        Set<ConstraintViolation<FieldCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("features")));
    }

    @Test
    public void testNullImagesPassValidation() {
        FieldCreateDTO dto = new FieldCreateDTO(
                "Cancha de Prueba", "Calle 123", UserZones.AVELLANEDA,
                List.of(FieldFeatures.GRASS), null);

        Set<ConstraintViolation<FieldCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testEmptyImagesPassValidation() {
        FieldCreateDTO dto = new FieldCreateDTO(
                "Cancha de Prueba", "Calle 123", UserZones.AVELLANEDA,
                List.of(FieldFeatures.GRASS), List.of());

        Set<ConstraintViolation<FieldCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

}
