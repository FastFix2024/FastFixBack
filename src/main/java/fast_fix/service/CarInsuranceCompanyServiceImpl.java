package fast_fix.service;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.entity.CarInsuranceCompany;
import fast_fix.domain.mapping.CarInsuranceCompanyMapper;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.repository.CarInsuranceCompanyRepository;
import fast_fix.service.interfaces.CarInsuranceCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarInsuranceCompanyServiceImpl implements CarInsuranceCompanyService {

    private CarInsuranceCompanyRepository carInsuranceCompanyRepository;

    private CarInsuranceCompanyMapper carInsuranceCompanyMapper;

    @Override
    public List<CarInsuranceCompanyDto> getAllCarInsuranceCompanies() {
        List<CarInsuranceCompany> companies = carInsuranceCompanyRepository.findAll();
        return companies.stream().map(carInsuranceCompanyMapper::toDto).collect(Collectors.toList());    }

    @Override
    public CarInsuranceCompanyDto getCarInsuranceCompanyById(Long id) {
        CarInsuranceCompany company = carInsuranceCompanyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car insurance company not found"));
        return carInsuranceCompanyMapper.toDto(company);    }

    @Override
    public CarInsuranceCompanyDto createCarInsuranceCompany(CarInsuranceCompanyDto carInsuranceCompanyDto) {
        CarInsuranceCompany company = carInsuranceCompanyMapper.toEntity(carInsuranceCompanyDto);
        CarInsuranceCompany savedCompany = carInsuranceCompanyRepository.save(company);
        return carInsuranceCompanyMapper.toDto(savedCompany);    }

    @Override
    public CarInsuranceCompanyDto updateCarInsuranceCompany(Long id, CarInsuranceCompanyDto carInsuranceCompanyDto) {
        CarInsuranceCompany existingCompany = carInsuranceCompanyRepository.findById(id).orElseThrow(() -> new RuntimeException("Car insurance company not found"));
        existingCompany.setName(carInsuranceCompanyDto.getName());
        existingCompany.setPhoneNumber(carInsuranceCompanyDto.getPhoneNumber());
        existingCompany.setWebsite(carInsuranceCompanyDto.getWebsite());
        CarInsuranceCompany updatedCompany = carInsuranceCompanyRepository.save(existingCompany);
        return carInsuranceCompanyMapper.toDto(updatedCompany);    }

    @Override
    public void deleteCarInsuranceCompany(Long id) {
        if (!carInsuranceCompanyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Car insurance company not found");
        }
        carInsuranceCompanyRepository.deleteById(id);
    }
}
