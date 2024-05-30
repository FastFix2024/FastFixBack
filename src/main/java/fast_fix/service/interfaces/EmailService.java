package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;

public interface EmailService {

    void sendRegistrationConfirmEmail(User user);
    void sendInsuranceChangedInfoEmail(User user);
    void sendNewEmailInfoEmail(User user);
    void sendFuelParamInfoEmail(User user);
    void sendTechInspectWarnEmail(User user);
    void sendPasswordChangedInfoEmail(User user);
    void sendChangePasswordRequestWarnEmail(User user);

//    void sendVerificationEmail(User user, String token) throws MessagingException;
//
//    void sendPasswordResetEmail(User user, String token);
//
//    void sendPromoEmail(User user, String token);
//
//    void sendReminderEmail(User user, String token);
}
