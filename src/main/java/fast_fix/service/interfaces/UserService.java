package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;

public interface UserService extends UserDetailsService {

    void registerUser(User user);
    UserDto findUserByEmail(String email);
    void deleteUserById(Long id);
    UserDto updateUserEmail(Long userId, String newEmail);
    void updatePassword(Long userId, String newPassword);
    UserDto updateFuelType(Long userId, String fuelType);
    UserDto updateInsuranceCompany(Long userId, CarInsuranceCompanyDto insuranceCompany);
    UserDto updateMaintenanceDate(Long userId, LocalDate nextMaintenanceDate);
    void logoutUser(Long userId);
    void sendMaintenanceReminder();
}