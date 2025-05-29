package ar.uba.fi.ingsoft1.todo_template.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/users")
@Tag(name = "1 - Users")
class UserRestController {
  
    private final UserService userService;

    @Autowired
    UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create a new user")
    public ResponseEntity<TokenDTO> createUser(@Valid @RequestBody UserCreateDTO data) {
        TokenDTO tokens = userService.createUser(data).orElseThrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(tokens);
    }

    @GetMapping("/verify-email")
    @Operation(
            summary = "Validación de correo electrónico",
            description = "Verifica el token enviado al correo electrónico del usuario durante el registro. Si el token es válido, se activa la cuenta del usuario."
    )
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        boolean verified = userService.verifyEmailToken(token);
        if (verified){
            return ResponseEntity.ok("Verified email");
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }




}