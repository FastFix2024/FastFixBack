package fast_fix.service;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.UserDto;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
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
    public UserDto updateInsuranceCompany(Long userId, CarInsuranceCompanyDto insuranceCompanyDto) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        user.getCarDetails().getInsuranceCompany().setName(insuranceCompanyDto.getName());
        user.getCarDetails().getInsuranceCompany().setPhoneNumber(insuranceCompanyDto.getPhoneNumber());
        user.getCarDetails().getInsuranceCompany().setWebsite(insuranceCompanyDto.getWebsite());
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
    public UserDto loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        } else {
            return userMapper.toDto(user);
        }
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
}