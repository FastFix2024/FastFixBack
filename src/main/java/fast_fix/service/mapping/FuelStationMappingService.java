package fast_fix.service.mapping;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.entity.FuelStation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FuelStationMappingService {

    FuelStationMappingService INSTANCE = Mappers.getMapper(FuelStationMappingService.class);

    FuelStationDto toDto(FuelStation fuelStation);
    FuelStation toEntity(FuelStationDto fuelStationDto);
}
