package fast_fix.domain.mapping;

import fast_fix.domain.dto.RoleDto;
import fast_fix.domain.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}