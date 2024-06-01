package fast_fix.domain.mapping;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CarDetailsMapper.class, RoleMapper.class})
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}