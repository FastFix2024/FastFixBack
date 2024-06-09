package fast_fix.service;

import fast_fix.domain.entity.ConfirmationCode;
import fast_fix.domain.entity.User;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.repository.ConfirmationCodeRepository;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.ConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    @Autowired
    private ConfirmationCodeRepository confirmationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    public String generateConfirmationCode(User user) {
        String code = UUID.randomUUID().toString();
        ConfirmationCode confirmationCode = new ConfirmationCode(code, LocalDateTime.now().plusMinutes(15), user);
        confirmationCodeRepository.save(confirmationCode);
        return code;
    }

    public User confirmUser(String code) {
        ConfirmationCode confirmationCode = confirmationCodeRepository.findByCode(code);
        if (confirmationCode == null || confirmationCode.getExpired().isBefore(LocalDateTime.now())) {
            throw new ResourceNotFoundException("Invalid confirmation code");
        }

        User user = confirmationCode.getUser();
        user.setConfirmed(true);
        userRepository.save(user);
        confirmationCodeRepository.delete(confirmationCode);
        return user;
    }
}