package fast_fix.service.interfaces;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    void registerUser(User user);
    User confirmUser(String code);
    UserDto getUserProfileByUsername(String username);
    UserDto getUserByUsername(String username);
    UserDto updateUserProfile(UserDto userDto);
    void deleteUserById(Long id);
}