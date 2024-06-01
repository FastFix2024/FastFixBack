package fast_fix.domain.mapping;

import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.domain.entity.ServiceStation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceStationMapper {

    ServiceStationDto toDto(ServiceStation serviceStation);

    ServiceStation toEntity(ServiceStationDto serviceStationDto);
}