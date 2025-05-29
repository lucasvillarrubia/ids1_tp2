package ar.uba.fi.ingsoft1.todo_template.user;

import ar.uba.fi.ingsoft1.todo_template.user.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "3 - Mail Validation")
public class EmailRepository {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public void senEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
    }
}

