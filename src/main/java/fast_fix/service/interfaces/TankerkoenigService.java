package fast_fix.service.interfaces;

import fast_fix.domain.dto.FuelStationDto;

import java.util.List;

public interface TankerkoenigService {
    List<String> getFuelTypes();
    List<FuelStationDto> getStationsNearby(String fuelType, double latitude, double longitude, int radius);
}