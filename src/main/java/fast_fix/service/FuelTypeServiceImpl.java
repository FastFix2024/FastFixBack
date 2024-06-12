package fast_fix.service;

import fast_fix.domain.dto.FuelTypeDto;
import fast_fix.domain.entity.FuelType;
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
}
