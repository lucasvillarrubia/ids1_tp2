package ar.uba.fi.ingsoft1.todo_template.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;


    public  void sendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
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
