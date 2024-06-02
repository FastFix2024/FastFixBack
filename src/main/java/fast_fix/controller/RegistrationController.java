package fast_fix.controller;

import fast_fix.domain.entity.User;
import fast_fix.exceptions.Response;
import fast_fix.service.interfaces.UserService;
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
    public fast_fix.exceptions.Response register(@RequestBody User user) {
        service.registerUser(user);
        return new Response("Registration complete. Please check your email");
    }
}