package fast_fix.controller;

import fast_fix.domain.dto.UserDto;
import fast_fix.service.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return service.getAllUsers();
    }
    @GetMapping
    public UserDto getById(@RequestParam Long id) {
        return service.getUsersById(id);
    }
}
