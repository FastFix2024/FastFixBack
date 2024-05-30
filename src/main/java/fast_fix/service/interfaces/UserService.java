package fast_fix.service.interfaces;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void registerUser(User user) throws MessagingException;
    UserDto save(UserDto dto);
    List<UserDto> getAllUsers();
    UserDto getUsersById(Long id);
    UserDto update(UserDto dto);
    void deleteById(Long id);
    void deleteByName(String name);
    long getUsersTotalCount();
    void addBookmarkToUserByIds(Long userId, Long bookmarkId);
    void deleteBookmarkFromUserByIds(Long userId, Long bookmarkId);
    void clearBookmarksByUserId(Long id);
    boolean existsByUsername(String username);
    void save(User user);
    String encodePassword(String rawPassword);

    boolean checkPassword(User user, String rawPassword);
    void changePassword(User user, String newPassword);
}
