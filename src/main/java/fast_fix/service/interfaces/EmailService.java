package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;

public interface EmailService {

    void sendConfirmationEmail(User user);
    void sendInsuranceChangedInfoEmail(User user);
    void sendNewEmailInfoEmail(User user);
    void sendFuelParamInfoEmail(User user);
    void sendTechInspectWarnEmail(User user);
    void sendChangePasswordRequestWarnEmail(User user);
    void sendPasswordChangedInfoEmail(User user);

}