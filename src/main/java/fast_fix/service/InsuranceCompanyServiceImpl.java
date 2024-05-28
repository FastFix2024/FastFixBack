package fast_fix.service;

import fast_fix.domain.dto.InsuranceCompanyDto;
import fast_fix.domain.entity.InsuranceCompany;
import fast_fix.exception_handling.exceptions.FourthTestException;
import fast_fix.exception_handling.exceptions.ResourceNotFoundException;
import fast_fix.exception_handling.exceptions.ThirdTestException;
import fast_fix.repository.InsuranceCompanyRepository;
import fast_fix.service.interfaces.InsuranceCompanyService;
import fast_fix.service.mapping.InsuranceCompanyMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {

    private InsuranceCompanyRepository repository;
    private InsuranceCompanyMappingService mappingService;

    @Autowired
    public InsuranceCompanyServiceImpl(InsuranceCompanyRepository repository, InsuranceCompanyMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public List<InsuranceCompanyDto> getAllCompanies() {
        return repository.findAll()
                .stream()
                .map(mappingService::insCompToInsCompDto)
                .toList();
    }

    @Override
    public InsuranceCompanyDto getCompanyById(Long id) {
        if (id == null || id < 1) {
            throw new ThirdTestException("Insurance company ID is incorrect");
        }

        InsuranceCompany insuranceCompany = repository.findById(id).orElse(null);

        if (insuranceCompany == null) {
            throw new RuntimeException("Insurance company not found");
        }

        return mappingService.insCompToInsCompDto(insuranceCompany);
    }

    @Override
    public InsuranceCompanyDto saveCompany(InsuranceCompanyDto company) {
        InsuranceCompany insuranceCompany = mappingService.insCompDtoToInsComp(company);

        try {
            repository.save(insuranceCompany);
        } catch (Exception e) {
            throw new FourthTestException("Saving insurance company error!", e);
        }

        return mappingService.insCompToInsCompDto(insuranceCompany);
    }

    @Override
    public InsuranceCompanyDto updateCompany(InsuranceCompanyDto updatedCompany) {
        Long companyId = updatedCompany.getId();
        InsuranceCompany existingCompany = repository.findById(companyId).orElse(null);

        if (existingCompany != null) {
            existingCompany.setCompanyName(updatedCompany.getCompanyName());
            existingCompany.setPhone(updatedCompany.getPhone());
            existingCompany.setWebsite(updatedCompany.getWebsite());

            return mappingService.insCompToInsCompDto(existingCompany);
        } else {
            throw new ResourceNotFoundException("Insurance company with ID " + companyId + " not found.");
        }
    }

    @Override
    public void deleteCompanyById(Long id) {
        repository.deleteById(id);

    }
}
