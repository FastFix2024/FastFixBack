package fast_fix.service;

import fast_fix.domain.entity.User;
import fast_fix.service.interfaces.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendVerificationEmail(User user, String token) throws MessagingException {
        String subject = "Подтверждение регистрации";
        String confirmationUrl = "http://localhost:8080/confirm?token=" + token;
        String message = "Перейдите по ссылке для подтверждения регистрации: " + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }

    @Override
    public void sendPasswordResetEmail(User user, String token) {

    }

    @Override
    public void sendPromoEmail(User user, String token) {

    }

    @Override
    public void sendReminderEmail(User user, String token) {

    }
}
