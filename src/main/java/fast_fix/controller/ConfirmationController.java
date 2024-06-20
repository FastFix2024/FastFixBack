package fast_fix.controller;

import fast_fix.domain.entity.User;
import fast_fix.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Tag(name = "Confirmation controller")
@RestController
@RequestMapping("/confirm")
public class ConfirmationController {

    private static final Logger logger = Logger.getLogger(ConfirmationController.class.getName());

    private UserService service;

    public ConfirmationController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Подтвердить email пользователя")
    @GetMapping
    public ResponseEntity<String> confirmEmail(@RequestParam("code") String code) {
        try {
            User user = service.confirmUser(code);
            if (user != null) {
                return ResponseEntity.ok("Thank you for confirming your email address!");
            } else {
                return ResponseEntity.status(400).body("Invalid confirmation code");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }
}