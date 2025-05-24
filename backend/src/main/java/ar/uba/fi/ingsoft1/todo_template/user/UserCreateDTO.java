package ar.uba.fi.ingsoft1.todo_template.user;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.function.Function;

public record UserCreateDTO(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String email,
        @NotBlank String name,
        @NotBlank String lastname,
        LocalDate birthday,
        @NotBlank String gender

) implements UserCredentials {
    public User asUser(Function<String, String> encryptPassword) {
        return new User(username, encryptPassword.apply(password), email, name, lastname, birthday, gender);
    }
}
