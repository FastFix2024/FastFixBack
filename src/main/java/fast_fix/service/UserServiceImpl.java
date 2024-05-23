package fast_fix.service;

import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.EmailService;
import fast_fix.service.interfaces.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void registerUser(User user) throws MessagingException {
        user.setActive(false);
        repository.save(user);

        String token = UUID.randomUUID().toString();
        emailService.sendVerificationEmail(user, token);
    }
}
