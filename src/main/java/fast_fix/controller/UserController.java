package fast_fix.controller;

import fast_fix.domain.dto.UserDto;
import fast_fix.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "User controller", description = "Controller for some operations with available users")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Получить пользователя по id")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Обновить пользователя")
    @PutMapping("/")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Вылогинить пользователя")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        userService.logoutUser();
        return ResponseEntity.ok().build();
    }
}