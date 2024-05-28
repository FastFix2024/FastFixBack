package fast_fix.service.mapping;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BookmarksMappingService.class)
public interface UserMappingService {

    @Mapping(target = "password", ignore = true)
    UserDto userToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User userDtoToUser(UserDto userDto);

}
