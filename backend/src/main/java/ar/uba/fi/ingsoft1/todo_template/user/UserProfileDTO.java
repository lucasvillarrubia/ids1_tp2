package ar.uba.fi.ingsoft1.todo_template.user;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserProfileDTO(
        @NotBlank String username,
        @NotBlank String name,
        @NotBlank String lastname,
        LocalDate birthday,
        @NotBlank String gender

){
    public UserProfileDTO(User user) {
        this(user.getUsername(), user.getName(), user.getLastname(), user.getBirthday(), user.getGender());
    }
}