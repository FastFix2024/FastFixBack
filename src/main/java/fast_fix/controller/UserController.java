package fast_fix.controller;

import fast_fix.domain.dto.UserDto;
import fast_fix.exceptions.Response;
import fast_fix.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "User controller", description = "Controller for some operations with available users")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Получить текущего пользователя")
    @GetMapping("/my/profile")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserDto userDto = userService.getUserProfileByUsername(principal.getName());
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Обновить текущего пользователя")
    @PutMapping("/my/profile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody UserDto userDto, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserDto updatedUser = userService.updateUserProfile(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Удалить текущего пользователя")
    @DeleteMapping("/my/profile")
    public ResponseEntity<?> deleteUser(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserDto currentUser = userService.getUserByUsername(principal.getName());
        if (currentUser != null) {
            userService.deleteUserById(currentUser.getId());
            return ResponseEntity.ok(new Response("User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Получить любого пользователя")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable String username) {
        UserDto userDto = userService.getUserProfileByUsername(username);
        return ResponseEntity.ok(userDto);
    }
}