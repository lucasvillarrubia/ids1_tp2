package ar.uba.fi.ingsoft1.todo_template.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.function.Function;

public record UserCreateDTO(
        @NotBlank(message = "Name is mandatory")
        @Size(max = 255)
        @Schema(description = "Name is mandatory", maxLength = 255, example = "John", required = true)
        String name,

        @NotBlank(message = "Last name is mandatory")
        @Size(max = 255)
        @Schema(description = "Last name is mandatory", maxLength = 255, example = "Doe", required = true)
        String lastname,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email format")
        @Size(max = 255)
        @Schema(description = "Email is mandatory and must be valid", maxLength = 255, example = "john@example.com", required = true)
        String email,

        @NotNull
        @NotEmpty
        List<UserZones> zones,

        @NotBlank(message = "Password is mandatory")
        @Schema(description = "Password is mandatory", required = true)
        String password,

        @Schema(description = "User photo URL", example = "http://example.com/photo.jpg")
        String photo,

        @NotBlank(message = "Gender is mandatory")
        @Schema(description = "Gender of the user", example = "male")
        String gender,

        @NotNull(message = "Age is required")
        @Min(value = 0, message = "Age must be a non-negative number")
        @Max(value = 150, message = "Age must be a reasonable number")
        Short age



) implements UserCredentials {
    public User asUser(Function<String, String> encryptPassword) {
        return new User(name, lastname, email, zones, encryptPassword.apply(password), gender, photo, age);
    }
}
