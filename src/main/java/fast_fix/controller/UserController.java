package fast_fix.controller;

import fast_fix.domain.entity.User;
import fast_fix.domain.entity.VerificationToken;
import fast_fix.repository.UserRepository;
import fast_fix.repository.VerificationTokenRepository;
import fast_fix.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

//TODO

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setActive(false);
        User savedUser = userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, savedUser);
        tokenRepository.save(verificationToken);

        String confirmationUrl = "http://localhost:8080/api/users/confirm?token=" + token;
        emailService.sendConfirmationEmail(user);

        return "Registration successful. Please check your email for confirmation link.";
    }

    @GetMapping("/confirm")
    public String confirmUser(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null || verificationToken.getExpiryDate().before(new Date())) {
            return "Invalid or expired token.";
        }

        User user = verificationToken.getUser();
        user.setActive(true);
        userRepository.save(user);

        return "Email confirmed successfully.";
    }
}
