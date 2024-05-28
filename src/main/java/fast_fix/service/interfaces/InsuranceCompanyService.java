package fast_fix.service.interfaces;

import fast_fix.domain.dto.InsuranceCompanyDto;
import fast_fix.domain.entity.InsuranceCompany;

import java.util.List;

public interface InsuranceCompanyService {

    List<InsuranceCompanyDto> getAllCompanies();
    InsuranceCompanyDto getCompanyById(Long id);
    InsuranceCompanyDto saveCompany(InsuranceCompanyDto company);
    InsuranceCompanyDto updateCompany(InsuranceCompanyDto company);
    void deleteCompanyById(Long id);
}
