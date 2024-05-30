package fast_fix.service;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.EmailService;
import fast_fix.service.interfaces.RoleService;
import fast_fix.service.interfaces.UserService;
import fast_fix.service.mapping.UserMappingService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    private final EmailService emailService;
    private final UserMappingService userMappingService;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder encoder, RoleService roleService, EmailService emailService, UserMappingService userMappingService) {
        this.repository = repository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.emailService = emailService;
        this.userMappingService = userMappingService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.isActive())
                .build();
    }


    @Override
    public boolean checkPassword(User user, String rawPassword) {
        return encoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        repository.save(user);
        try {
            emailService.sendChangePasswordRequestWarnEmail(user);
            emailService.sendPasswordChangedInfoEmail(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void registerUser(User user) throws MessagingException {
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.getRoleUser()));
        user.setActive(false);

        repository.save(user);
        emailService.sendRegistrationConfirmEmail(user);
    }

    @Override
    public UserDto save(UserDto dto) {
        User user = userMappingService.userDtoToUser(dto);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        user = repository.save(user);
        return userMappingService.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> activeUsers = repository.findAll();
        return activeUsers.stream()
                .map(userMappingService::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUsersById(Long id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("User with ID: " + id + " not found");
        }
        return userMappingService.userToUserDto(user);
    }

    @Override
    public UserDto update(UserDto dto) {
        User userToUpdate = repository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("User with ID: " + dto.getId() + " not found"));

        if (dto.getUsername() != null) {
            userToUpdate.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            userToUpdate.setEmail(dto.getEmail());
        }
        if (dto.getRoles() != null) {
            userToUpdate.setRoles(dto.getRoles());
        }

        userToUpdate = repository.save(userToUpdate);
        return userMappingService.userToUserDto(userToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        User userToDelete = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " not found"));
        repository.delete(userToDelete);
    }

    @Override
    public void deleteByName(String name) {
        User userToDelete = repository.findByUsername(name);
        if (userToDelete == null) {
            throw new EntityNotFoundException("User with username: " + name + " not found");
        }
        repository.delete(userToDelete);
    }

    @Override
    public long getUsersTotalCount() {
        return repository.count();
    }

    @Override
    public void addBookmarkToUserByIds(Long userId, Long bookmarkId) {
        // Реализация добавления закладки пользователю
    }

    @Override
    public void deleteBookmarkFromUserByIds(Long userId, Long bookmarkId) {
        // Реализация удаления закладки у пользователя
    }

    @Override
    public void clearBookmarksByUserId(Long id) {
        // Реализация очистки всех закладок у пользователя
    }
}

