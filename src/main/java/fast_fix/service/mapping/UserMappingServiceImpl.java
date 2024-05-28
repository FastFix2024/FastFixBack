package fast_fix.service.mapping;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;

import org.springframework.stereotype.Service;

@Service
public class UserMappingServiceImpl implements UserMappingService {

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User entity = new User();
        entity.setId(userDto.getId());
        entity.setUsername(userDto.getUsername());
        entity.setEmail(userDto.getEmail());
        entity.setRoles(userDto.getRoles());
        return entity;
    }
}
