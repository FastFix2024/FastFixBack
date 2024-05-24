package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;
import jakarta.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
//    User getById(Long id);
//    User save(User user);

    void registerUser(User user) throws MessagingException;
}