package ar.uba.fi.ingsoft1.todo_template.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

public record UserProfileDTO(
        @Schema(description = "ID del usuario")
        Long id,
        @Schema(description = "Nombre", maxLength = 255, example = "Juan")
        String name,
        @Schema(description = "Apellido", maxLength = 255, example = "Lopez")
        String lastname,
        @Schema(description = "Email del usuario", maxLength = 255, example = "lopezjuan@gmail.com")
        String email,
        @Schema(description = "Edad del usuario", maxLength = 255, example = "23")
        Short age,

        @Schema(description = "Género del usuario", maxLength = 255, example = "Masculino")
        String gender,

        @Schema(description ="Zonas en las que participa",example = "[\"VICENTE_LOPEZ\", \"SAN_ISIDRO\"]" )
        List<UserZones> zones,

        @Schema(description = "URL foto de perfil", example = "https://example.com/profile.jpg")
        String photo
) {

    public static UserProfileDTO fromUser(User user) {
        List<String> collectedZoneNames = user.getZones()
                .stream()
                .map(UserZones::name)
                .toList();

        UserProfileDTO userProfileDTO = new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getAge(),
                user.getGender(),
                user.getZones(),
                user.getPhoto()
                ); return userProfileDTO;
    }
}
