package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendConfirmationEmail(User user);

//    void sendVerificationEmail(User user, String token) throws MessagingException;
//
//    void sendPasswordResetEmail(User user, String token);
//
//    void sendPromoEmail(User user, String token);
//
//    void sendReminderEmail(User user, String token);
}
