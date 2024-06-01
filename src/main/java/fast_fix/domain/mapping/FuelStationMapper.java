package fast_fix.domain.mapping;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import fast_fix.domain.entity.FuelStation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelStationMapper {

    FuelStationDto toDto(FuelStationResponse.Station station);

    FuelStation toEntity(FuelStationDto fuelStationDto);
}