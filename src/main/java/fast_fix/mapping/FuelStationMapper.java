package fast_fix.mapping;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelStationMapper {
    FuelStationDto toDto(FuelStationResponse.Station station);
    FuelStationResponse.Station toStation(FuelStationDto fuelStationDto);
}