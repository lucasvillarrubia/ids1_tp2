package ar.uba.fi.ingsoft1.todo_template.user;

import java.util.List;
import java.util.stream.Collectors;

public record UserProfileDTO(
        Long id,
        String name,
        String lastname,
        String email,
        Short age,
        String gender,
        List<UserZones> zones,
        String photo
) {

    public static UserProfileDTO fromUser(User user) {
        List<String> collectedZoneNames = user.getZones()
                .stream()
                .map(UserZones::name)
                .collect(Collectors.toList());

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
