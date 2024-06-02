package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;

public interface ConfirmationService {
    String generateConfirmationCode(User user);
}