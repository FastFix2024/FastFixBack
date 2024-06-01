package fast_fix.domain.mapping;

import fast_fix.domain.dto.RouteDto;
import fast_fix.domain.entity.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface RouteMapper {

    RouteDto toDto(Route route);

    Route toEntity(RouteDto routeDto);
}