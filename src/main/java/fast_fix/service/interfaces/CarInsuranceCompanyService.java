package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarInsuranceCompanyDto;

import java.util.List;

public interface CarInsuranceCompanyService {
    List<CarInsuranceCompanyDto> getAllCarInsuranceCompanies();
    CarInsuranceCompanyDto getCarInsuranceCompanyById(Long id);
}