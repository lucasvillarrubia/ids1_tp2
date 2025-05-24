package ar.uba.fi.ingsoft1.todo_template.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.function.Function;

public record UserCreateDTO(
        @NotBlank(message = "Username is mandatory")
        @Size(max = 255)
        @Schema(description = "Username is mandatory", maxLength = 255, example = "john_doe", required = true)
        String username,

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

        @NotBlank(message = "Zone is mandatory")
        @Schema(description = "Zone is mandatory", example = "US", required = true)
        String zone,

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
        return new User(username, name, lastname, email, zone, encryptPassword.apply(password), gender, photo, age);
    }
}
