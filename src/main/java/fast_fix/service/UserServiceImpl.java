package fast_fix.service;

import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.CarInsuranceCompany;
import fast_fix.domain.entity.User;
import fast_fix.domain.mapping.UserMapper;
import fast_fix.exceptions.BadRequestException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.exceptions.UnauthorizedException;
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
        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void confirmEmail(String token) {

    }

    @Override
    public UserDto updateUserEmail(Long userId, String newEmail) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        user.setEmail(newEmail);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
            user.setPassword(newPassword);
            userRepository.save(user);
    }

    @Override
    public void requestPasswordReset(String email) {

    }

    @Override
    public UserDto updateFuelType(Long userId, String fuelType) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
            user.getCarDetails().setFuelType(fuelType);
            user = userRepository.save(user);
            return userMapper.toDto(user);
    }

    @Override
    public UserDto updateInsuranceCompany(Long userId, CarInsuranceCompany insuranceCompany) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
            user.getCarDetails().setInsuranceCompany(insuranceCompany);
            user = userRepository.save(user);
            return userMapper.toDto(user);
    }

    @Override
    public UserDto updateMaintenanceDate(Long userId, LocalDate nextMaintenanceDate) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
            user.getCarDetails().setLastMaintenanceDate(nextMaintenanceDate);
            user = userRepository.save(user);
            return userMapper.toDto(user);
    }

    @Override
    public void sendEmailNotification(Long userId, String subject, String message) {

    }

    @Override
    public UserDto loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new UnauthorizedException("Invalid email or password");
        }
        return userMapper.toDto(user);
    }

    @Override
    public void logoutUser(Long userId) {

    }

    @Override
    public List<ServiceStationDto> getServiceStationsNearUser(Long userId, double radius, String type) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        BigDecimal latitude = user.getLat();
        BigDecimal longitude = user.getLng();
        return serviceStationService.getServiceStationsByLocation(latitude, longitude, radius, type);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return (UserDetails) user;
    }
}