package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelTypeDto;

import java.time.LocalDate;
import java.util.List;

public interface CarDetailsService {
    CarDetailsDto getCarDetails(Long userId);

    CarDetailsDto updateFuelType(Long userId, Long fuelTypeId);

    CarDetailsDto updateInsuranceCompany(Long userId, Long insuranceCompanyId);

    CarDetailsDto updateLastMaintenanceDate(Long userId, LocalDate lastMaintenanceDate);

    List<FuelTypeDto> getFuelTypes();

    List<CarInsuranceCompanyDto> getAllInsuranceCompanies();
}