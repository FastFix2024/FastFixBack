package fast_fix.controller;

import fast_fix.domain.entity.User;
import fast_fix.exceptions.ConflictException;
import fast_fix.exceptions.Response;
import fast_fix.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Registration controller")
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Зарегистрировать пользователя")
    @PostMapping
    public fast_fix.exceptions.Response register(@RequestBody User user) {
        try {
            service.registerUser(user);
        } catch (Exception e) {
            throw new ConflictException("Username or email already exists");
        }
        return new Response("Registration complete. Please check your email");
    }
}