package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import fast_fix.domain.entity.CarInsuranceCompany;

import java.time.LocalDate;
import java.util.List;

public interface CarDetailsService {
    CarDetailsDto getCarDetails(Long userId);
    CarDetailsDto updateFuelType(Long userId, String fuelType);
    CarDetailsDto updateInsuranceCompany(Long userId, Long insuranceCompanyId);
    CarDetailsDto updateLastMaintenanceDate(Long userId, LocalDate lastMaintenanceDate);
    List<String> getFuelTypes();
    List<FuelStationDto> getStationsNearby(String fuelType, double latitude, double longitude, int radius);
    List<CarInsuranceCompanyDto> getAllInsuranceCompanies();
}