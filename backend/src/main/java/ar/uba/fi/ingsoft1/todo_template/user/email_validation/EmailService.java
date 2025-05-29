package ar.uba.fi.ingsoft1.todo_template.user.email_validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class EmailService {

    private JavaMailSender mailSender;
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public  void sendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ejemplo@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Async

    public void sendValidationEmail(String to, String validationToken){
        String subject = "Validation Email";
        String validationLink = "http://localhost:8080/api/v1/users/" + to + "/validations/" + validationToken;
        sendEmail(to, subject, validationLink);
    }


}
