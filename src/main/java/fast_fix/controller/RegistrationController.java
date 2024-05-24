package fast_fix.controller;

import fast_fix.service.interfaces.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }

    //todo
//    @PostMapping
//    public Response register(@RequestBody User user) {
//        service.register(user);
//        return new Response("Registration complete. Please check your email");
//    }
}
