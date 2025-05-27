package ar.uba.fi.ingsoft1.todo_template.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO (
        @NotBlank String email,
        @NotBlank String password
) implements UserCredentials {}
