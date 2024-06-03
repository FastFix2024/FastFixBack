package fast_fix.service;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.CarDetails;
import fast_fix.domain.entity.User;
import fast_fix.domain.mapping.UserMapper;
import fast_fix.exceptions.BadRequestException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.exceptions.UnauthorizedException;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.EmailService;
import fast_fix.service.interfaces.RoleService;
import fast_fix.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private RoleService roleService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.getRoleUser()));
        user.setActive(false);

        userRepository.save(user);
        emailService.sendConfirmationEmail(user);
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
        emailService.sendDeleteAccountInfoEmail(userRepository.findUserById(id));
    }

    @Override
    public UserDto updateUserEmail(Long userId, String newEmail) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        user.setEmail(newEmail);
        user = userRepository.save(user);
        emailService.sendNewEmailInfoEmail(user);
        return userMapper.toDto(user);
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        emailService.sendPasswordChangedInfoEmail(user);
        userRepository.save(user);
    }

    @Override
    public UserDto updateFuelType(Long userId, String fuelType) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        CarDetails carDetails = user.getCarDetails();
        if (carDetails == null) {
            throw new ResourceNotFoundException("Car details not found");
        }
        carDetails.setFuelType(fuelType);
        userRepository.save(user);
        emailService.sendFuelParamInfoEmail(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateInsuranceCompany(Long userId, CarInsuranceCompanyDto insuranceCompanyDto) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        CarDetails carDetails = user.getCarDetails();
        if (carDetails == null) {
            throw new ResourceNotFoundException("Car details not found");
        }
        carDetails.getInsuranceCompany().setName(insuranceCompanyDto.getName());
        carDetails.getInsuranceCompany().setPhoneNumber(insuranceCompanyDto.getPhoneNumber());
        carDetails.getInsuranceCompany().setWebsite(insuranceCompanyDto.getWebsite());
        userRepository.save(user);
        emailService.sendInsuranceChangedInfoEmail(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateMaintenanceDate(Long userId, LocalDate nextMaintenanceDate) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        CarDetails carDetails = user.getCarDetails();
        if (carDetails == null) {
            throw new ResourceNotFoundException("Car details not found");
        }
        carDetails.setLastMaintenanceDate(nextMaintenanceDate);
        userRepository.save(user);
        emailService.sendMaintenanceDateChangedEmail(user);
        return userMapper.toDto(user);
    }

    //TODO
    @Override
    public void logoutUser(Long userId) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?") // Запускается каждый день в полночь
    public void sendMaintenanceReminder() {
        LocalDate now = LocalDate.now();
        LocalDate reminderDate = now.plusMonths(1);

        List<User> users = userRepository.findAll();
        for (User user : users) {
            CarDetails carDetails = user.getCarDetails();
            if (carDetails != null && carDetails.getLastMaintenanceDate() != null) {
                LocalDate maintenanceDate = carDetails.getLastMaintenanceDate();
                if (maintenanceDate.isEqual(reminderDate) || maintenanceDate.isBefore(reminderDate)) {
                    emailService.sendMaintenanceReminderEmail(user);
                }
            }
        }
    }
}