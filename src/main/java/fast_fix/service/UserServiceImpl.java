package fast_fix.service;

import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.CarInsuranceCompany;
import fast_fix.domain.entity.User;
import fast_fix.domain.mapping.UserMapper;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.ServiceStationService;
import fast_fix.service.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private ServiceStationService serviceStationService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void confirmEmail(String token) {

    }

    @Override
    public UserDto updateUserEmail(Long userId, String newEmail) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            user.setEmail(newEmail);
            user = userRepository.save(user);
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    @Override
    public void requestPasswordReset(String email) {

    }

    @Override
    public UserDto updateFuelType(Long userId, String fuelType) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            user.getCarDetails().setFuelType(fuelType);
            user = userRepository.save(user);
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public UserDto updateInsuranceCompany(Long userId, CarInsuranceCompany insuranceCompany) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            user.getCarDetails().setInsuranceCompany(insuranceCompany);
            user = userRepository.save(user);
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public UserDto updateMaintenanceDate(Long userId, LocalDate nextMaintenanceDate) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            user.getCarDetails().setLastMaintenanceDate(nextMaintenanceDate);
            user = userRepository.save(user);
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public void sendEmailNotification(Long userId, String subject, String message) {

    }

    @Override
    public UserDto loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public void logoutUser(Long userId) {

    }

    @Override
    public List<ServiceStationDto> getServiceStationsNearUser(Long userId, double radius, String type) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            BigDecimal latitude = user.getLat();
            BigDecimal longitude = user.getLng();
            return serviceStationService.getServiceStationsByLocation(latitude, longitude, radius, type);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) user;
    }
}