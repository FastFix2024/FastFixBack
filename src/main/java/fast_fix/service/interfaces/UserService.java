package fast_fix.service.interfaces;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    void registerUser(User user);
    UserDto getUser(Long userId);
    void logoutUser();
    void deleteUserById(Long id);
    UserDto updateUser(UserDto userDto);
}