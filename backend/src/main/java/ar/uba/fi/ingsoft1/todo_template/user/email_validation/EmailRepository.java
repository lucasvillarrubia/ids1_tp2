package ar.uba.fi.ingsoft1.todo_template.user.email_validation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "3 - Mail Validation",description = "Endpoints para el envío de correos electrónicos")
public class EmailRepository {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    @Operation(
            summary = "Enviar correo electrónico",
            description = "Permite enviar un email a una dirección especificada, con un asunto y contenido definidos. Se usa para validar cuentas u otras notificaciones automáticas."
    )
    public void senEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
    }
}

