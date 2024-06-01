package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.CarInsuranceCompany;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto registerUser(UserDto userDto);
    UserDto findUserByEmail(String email);
    void deleteUserById(Long id);
    void confirmEmail(String token);
    UserDto updateUserEmail(Long userId, String newEmail);
    void updatePassword(Long userId, String newPassword);
    void requestPasswordReset(String email);
    UserDto updateFuelType(Long userId, String fuelType);
    UserDto updateInsuranceCompany(Long userId, CarInsuranceCompany insuranceCompany);
    UserDto updateMaintenanceDate(Long userId, LocalDate nextMaintenanceDate);
    void sendEmailNotification(Long userId, String subject, String message);
    UserDto loginUser(String email, String password);
    void logoutUser(Long userId);
    List<ServiceStationDto> getServiceStationsNearUser(Long userId, double radius, String type);
}