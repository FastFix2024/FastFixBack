package fast_fix.controller;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto userDto = userService.findUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<UserDto> updateUserEmail(@PathVariable Long userId, @RequestParam String newEmail) {
        UserDto updatedUser = userService.updateUserEmail(userId, newEmail);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestParam String newPassword) {
        userService.updatePassword(userId, newPassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/fuel-type")
    public ResponseEntity<UserDto> updateFuelType(@PathVariable Long userId, @RequestParam String fuelType) {
        UserDto updatedUser = userService.updateFuelType(userId, fuelType);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/insurance-company")
    public ResponseEntity<UserDto> updateInsuranceCompany(@PathVariable Long userId, @RequestBody CarInsuranceCompanyDto insuranceCompanyDto) {
        UserDto updatedUser = userService.updateInsuranceCompany(userId, insuranceCompanyDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/maintenance-date")
    public ResponseEntity<UserDto> updateMaintenanceDate(@PathVariable Long userId, @RequestParam LocalDate nextMaintenanceDate) {
        UserDto updatedUser = userService.updateMaintenanceDate(userId, nextMaintenanceDate);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestParam Long userId) {
//        TODO
        userService.logoutUser(userId);
        return ResponseEntity.ok().build();
    }
}