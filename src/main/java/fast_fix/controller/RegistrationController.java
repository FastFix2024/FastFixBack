package fast_fix.controller;

import fast_fix.domain.entity.User;
import fast_fix.exception_handling.Response;
import fast_fix.service.interfaces.UserService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }
    @PostMapping
    public Response register(@RequestBody User user) throws MessagingException {
        service.registerUser(user);
        return new Response("Registration complete. Please check your email.");
    }
}
