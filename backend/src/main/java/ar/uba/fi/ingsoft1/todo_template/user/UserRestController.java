package ar.uba.fi.ingsoft1.todo_template.user;

import ar.uba.fi.ingsoft1.todo_template.ratings.RatingDTO;
import ar.uba.fi.ingsoft1.todo_template.ratings.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "1 - Users")
class UserRestController {
  
    private final UserService userService;
    private final RatingService ratingService;

    @Autowired
    UserRestController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create a new user")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TokenDTO> signUp(
            @Valid @NonNull @RequestBody UserCreateDTO data
    ) throws MethodArgumentNotValidException {
        return userService.createUser(data)
                .map(tk -> ResponseEntity.status(HttpStatus.CREATED).body(tk))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


    @GetMapping(value = "/{username}", produces = "application/json")
    @Operation(summary = "Search an user by username")
    @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    public ResponseEntity<UserDetails> getUserByUsername(
        @PathVariable String username
    ) throws MethodArgumentNotValidException {
        UserDetails user = userService.loadUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{username}/profile", produces = "text/plain")
    @Operation(summary = "Get user profile by username")
    @ApiResponse(responseCode = "200", description = "User profile found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    public ResponseEntity<UserProfileDTO> getProfileByUsername(
        @RequestBody @Valid @PathVariable String username
    ) {
        Optional<UserProfileDTO> user = userService.getUserProfile(username);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{username}/ratings", produces = "application/json")
    @Operation(summary = "Get user ratings")
    @ApiResponse(responseCode = "200", description = "User ratings found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "204", description = "No ratings found", content = @Content)
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    public ResponseEntity<List<RatingDTO>> getUserRatings(@NotBlank @PathVariable("username") String userName) {
        List<RatingDTO> ratings = ratingService.getRatingsByUser(userName);
        return ratings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(ratings);
    }

    @PatchMapping(value = "/{id}/profile", produces = "text/plain")
    @Operation(summary = "Get user profile by username")
    @ApiResponse(responseCode = "200", description = "User profile found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    public ResponseEntity<UserProfileDTO> updateProfileByUsername(
        @RequestBody @Valid UserProfileDTO userProfileDTO
    ) {
        User user = userService.updateUserProfile(userProfileDTO);
        return ResponseEntity.ok(new UserProfileDTO(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/admin", produces = "application/json")
    @Operation(summary = "Create a new admin user")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TokenDTO> signUpAdmin(
            @Valid @NonNull @RequestBody UserCreateDTO data
    ) throws MethodArgumentNotValidException {
        return userService.createAdmin(data)
                .map(tk -> ResponseEntity.status(HttpStatus.CREATED).body(tk))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/admin/{username}", produces = "application/json")
    @Operation(summary = "Edit an existing admin user")
    @ApiResponse(responseCode = "200", description = "Admin updated")
    @ApiResponse(responseCode = "404", description = "Admin not found")
    public ResponseEntity<Void> updateAdmin(
            @PathVariable String username,
            @Valid @RequestBody UserCreateDTO data
    ) {
        return userService.updateAdmin(username, data)
                .map(user -> ResponseEntity.ok().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user")
    @ApiResponse(responseCode = "204", description = "Admin deleted")
    @ApiResponse(responseCode = "404", description = "Admin not found")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        boolean deleted = userService.deleteUser(username);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{username}")
    @Operation(summary = "Delete an admin user")
    @ApiResponse(responseCode = "204", description = "Admin deleted")
    @ApiResponse(responseCode = "404", description = "Admin not found")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String username) {
        boolean deleted = userService.deleteAdmin(username);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/followers/request")
    @Operation(summary = "Aceptar o rechazar solicitud de seguimiento")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Map<String, String>> manejarSolicitud(
            @PathVariable Long id,
            @RequestParam Long solicitanteId,
            @RequestParam boolean aceptar
    ) {
        try {
            boolean confirmacion = userService.manejoSolicitudesSeguimiento(id, solicitanteId, aceptar);
            if (!confirmacion) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Solicitud no encontrada o usuario inválido"));
            }

            String mensaje = aceptar ? "Solicitud aceptada" : "Solicitud rechazada";
            return ResponseEntity.ok(Map.of("message", mensaje));
        } catch (UsernameNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}/seguidores")
    @Operation(summary = "Ver lista de seguidores")
    @ApiResponse(responseCode = "401", description = "User not log")
    public ResponseEntity<List<UserProfileDTO>> getSeguidores(@PathVariable String username){
        return ResponseEntity.ok(userService.getSeguidores(username));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}/seguidos")
    @Operation(summary = "Ver lista de seguidos")
    @ApiResponse(responseCode = "401", description = "User not log")
    public ResponseEntity<List<UserProfileDTO>> getSeguidos (@PathVariable String username){
        return ResponseEntity.ok(userService.getSeguidos(username));
    }








}
