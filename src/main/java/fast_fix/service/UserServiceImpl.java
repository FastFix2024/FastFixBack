package fast_fix.service;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.CarDetails;
import fast_fix.domain.entity.User;
import fast_fix.exceptions.ConflictException;
import fast_fix.mapping.CarDetailsMapper;
import fast_fix.mapping.CarInsuranceCompanyMapper;
import fast_fix.mapping.UserMapper;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.repository.ConfirmationCodeRepository;
import fast_fix.repository.RoleRepository;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.ConfirmationService;
import fast_fix.service.interfaces.EmailService;
import fast_fix.service.interfaces.RoleService;
import fast_fix.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ConfirmationCodeRepository confirmationCodeRepository;

    @Autowired
    private ConfirmationService confirmationService;

    @Autowired
    private CarDetailsMapper carDetailsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CarInsuranceCompanyMapper carInsuranceCompanyMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ConflictException("User with this email already exists");
        }
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new ConflictException("User with this username already exists");
        }

        user.setId(null);
        user.setConfirmed(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.getRoleUser()));

        userRepository.save(user);

        String confirmationCode = confirmationService.generateConfirmationCode(user);
        emailService.sendConfirmationEmail(user, confirmationCode);
    }

    @Override
    public User confirmUser(String code) {
        logger.log(Level.INFO, "Attempting to confirm user with code: {0}", code);
        try {
            User user = confirmationService.confirmUser(code);
            if (user != null) {
                logger.log(Level.INFO, "User {0} confirmed successfully", user.getUsername());
            } else {
                logger.log(Level.WARNING, "Invalid confirmation code: {0}", code);
            }
            return user;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while confirming user with code: " + code, e);
            throw new RuntimeException("An error occurred while confirming user", e);
        }
    }

    @Override
    public UserDto getUserProfileByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        UserDto userDto = userMapper.toDto(user);
        CarDetails carDetails = user.getCarDetails();
        if (carDetails != null) {
            CarDetailsDto carDetailsDto = new CarDetailsDto();
            carDetailsDto.setFuelType(carDetails.getFuelType());
            if (carDetails.getInsuranceCompany() != null) {
                carDetailsDto.setInsuranceCompany(carInsuranceCompanyMapper.toDto(carDetails.getInsuranceCompany()));
            }
            carDetailsDto.setLastMaintenanceDate(carDetails.getLastMaintenanceDate());

            userDto.setCarDetails(carDetailsDto);
        }

        return userDto;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("You have to be logged in to delete your account");
        }

        String currentUsername = authentication.getName();
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userToDelete.getUsername().equals(currentUsername) &&
                !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new AccessDeniedException("You don't have permission to delete this account");
        }

        // Удаляем все роли пользователя
        confirmationCodeRepository.deleteByUserId(userId);
        roleRepository.deleteUserRolesById(userId);

        // Удаляем пользователя
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUserProfile(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("You must be logged in to update user details.");
        }
        String currentUsername = authentication.getName();
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!existingUser.getUsername().equals(currentUsername) && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new AccessDeniedException("You are not authorized to update this user's details.");
        }

        boolean isUpdated = false;
        if (!Objects.equals(existingUser.getEmail(), userDto.getEmail())) {
            existingUser.setEmail(userDto.getEmail());
            isUpdated = true;
        }
        if (!Objects.equals(existingUser.getUsername(), userDto.getUsername())) {
            existingUser.setUsername(userDto.getUsername());
            isUpdated = true;
        }
        CarDetails existingCarDetails = existingUser.getCarDetails();
        CarDetailsDto newCarDetailsDto = userDto.getCarDetails();

        if (existingCarDetails == null && newCarDetailsDto != null) {
            existingUser.setCarDetails(carDetailsMapper.toEntity(newCarDetailsDto));
            isUpdated = true;
        } else if (existingCarDetails != null && newCarDetailsDto != null) {
            if (!Objects.equals(existingCarDetails.getFuelType(), newCarDetailsDto.getFuelType())) {
                existingCarDetails.setFuelType(newCarDetailsDto.getFuelType());
                isUpdated = true;
            }
            if (newCarDetailsDto.getInsuranceCompany() != null && !Objects.equals(existingCarDetails.getInsuranceCompany(), newCarDetailsDto.getInsuranceCompany())) {
                existingCarDetails.setInsuranceCompany(carInsuranceCompanyMapper.toEntity(newCarDetailsDto.getInsuranceCompany()));
                isUpdated = true;
            }
            if (newCarDetailsDto.getLastMaintenanceDate() != null && !Objects.equals(existingCarDetails.getLastMaintenanceDate(), newCarDetailsDto.getLastMaintenanceDate())) {
                existingCarDetails.setLastMaintenanceDate(newCarDetailsDto.getLastMaintenanceDate());
                isUpdated = true;
            }
        }

        if (isUpdated) {
            userRepository.save(existingUser);
        }

        return userMapper.toDto(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }
}