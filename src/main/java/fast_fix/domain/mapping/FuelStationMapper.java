package fast_fix.domain.mapping;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuelStationMapper {

    FuelStationDto toDto(FuelStationResponse.Station station);

    List<FuelStationDto> toDtoList(List<FuelStationResponse.Station> stations);
}