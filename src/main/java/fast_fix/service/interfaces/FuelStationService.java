package fast_fix.service.interfaces;

import fast_fix.domain.dto.FuelStationDto;

import java.util.List;

public interface FuelStationService {

    FuelStationDto[] getAllFuelStations();
    List<FuelStationDto> getFuelStations(String location);
    List<FuelStationDto> findNearby(double latitude, double longitude, double radius);

    FuelStationDto save(FuelStationDto fuelStationDto);


}
