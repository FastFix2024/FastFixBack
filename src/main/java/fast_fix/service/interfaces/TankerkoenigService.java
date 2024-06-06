package fast_fix.service.interfaces;

import fast_fix.domain.dto.FuelStationDto;

import java.util.List;

public interface TankerkoenigService {
    List<FuelStationDto> getStationsNearby(double lat, double lng, int rad, String fuelType);
}