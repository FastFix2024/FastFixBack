package fast_fix.service;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelTypeDto;
import fast_fix.domain.entity.CarInsuranceCompany;
import fast_fix.domain.entity.FuelType;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.mapping.FuelTypeMapper;
import fast_fix.repository.FuelTypeRepository;
import fast_fix.service.interfaces.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private FuelTypeMapper fuelTypeMapper;

    @Override
    public List<FuelTypeDto> getAllFuelTypes() {
        List<FuelType> fuelTypes = fuelTypeRepository.findAll();
        return fuelTypes.stream()
                .map(fuelTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FuelTypeDto getFuelTypeById(Long id) {
        FuelType fuelType = fuelTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fuel type not found"));
        return fuelTypeMapper.toDto(fuelType);
    }
}
