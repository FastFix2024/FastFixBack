package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelTypeDto;

import java.util.List;

public interface FuelTypeService {

    List<FuelTypeDto> getAllFuelTypes();
    FuelTypeDto getFuelTypeById(Long id);
}