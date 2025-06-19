package ar.uba.fi.ingsoft1.todo_template.user;

import jakarta.validation.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileDTOTest {
    private Validator validator;
    private User user;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UserProfileDTO buildValidDTO() {
        return new UserProfileDTO(
                1L,
                "Juan",
                "Lopez",
                "lopezjuan@gmail.com",
                (short) 23,
                "Masculino",
                List.of(UserZones.VICENTE_LOPEZ, UserZones.SAN_ISIDRO),
                "https://example.com/profile.jpg"
        );
    }

    @Test
    public void testFromUser_CopiesAllFieldsCorrectly() {
        UserProfileDTO dto = buildValidDTO();

        assertEquals(1L, dto.id());
        assertEquals("Juan", dto.name());
        assertEquals("Lopez", dto.lastname());
        assertEquals("lopezjuan@gmail.com", dto.email());
        assertEquals((short) 23, dto.age());
        assertEquals("Masculino", dto.gender());
        assertEquals(List.of(UserZones.VICENTE_LOPEZ, UserZones.SAN_ISIDRO), dto.zones());
        assertEquals("https://example.com/profile.jpg", dto.photo());
    }

    @Test
    public void testNullPhotoIsAccepted() {
        UserProfileDTO dto = new UserProfileDTO(
                1L, "Juan", "Lopez", "lopezjuan@gmail.com",
                (short) 23, "Masculino", List.of(UserZones.VICENTE_LOPEZ), null
        );

        Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNegativeAgeFailsValidation() {
        UserProfileDTO dto = new UserProfileDTO(
                1L, "Juan", "Lopez", "lopezjuan@gmail.com",
                (short) -5, "Masculino",
                List.of(UserZones.VICENTE_LOPEZ), "https://example.com/profile.jpg"
        );

        Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("age")));
    }

    @Test
    public void testNullZonesIsAccepted() {
        UserProfileDTO dto = new UserProfileDTO(
                1L, "Juan", "Lopez", "lopezjuan@gmail.com",
                (short) 23, "Masculino", null, "https://example.com/profile.jpg"
        );

        Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }




}
