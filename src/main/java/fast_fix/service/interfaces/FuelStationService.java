package fast_fix.service.interfaces;

import fast_fix.domain.dto.FuelStationDto;

import java.math.BigDecimal;
import java.util.List;

public interface FuelStationService {
    List<FuelStationDto> getNearbyFuelStations(BigDecimal latitude, BigDecimal longitude, double radius, String type);
}