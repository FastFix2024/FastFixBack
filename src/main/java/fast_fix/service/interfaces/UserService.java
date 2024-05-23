package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;
import jakarta.mail.MessagingException;

public interface UserService {
    User getById(Long id);

    User save(User user);

    void registerUser(User user) throws MessagingException;

}
