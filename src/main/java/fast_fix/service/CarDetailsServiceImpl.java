package fast_fix.service;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelTypeDto;
import fast_fix.domain.entity.CarDetails;
import fast_fix.domain.entity.CarInsuranceCompany;
import fast_fix.domain.entity.FuelType;
import fast_fix.domain.entity.User;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.mapping.CarDetailsMapper;
import fast_fix.mapping.CarInsuranceCompanyMapper;
import fast_fix.mapping.FuelTypeMapper;
import fast_fix.repository.CarDetailsRepository;
import fast_fix.repository.CarInsuranceCompanyRepository;
import fast_fix.repository.FuelTypeRepository;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.CarDetailsService;
import fast_fix.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDetailsServiceImpl implements CarDetailsService {

    @Autowired
    private CarDetailsRepository carDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarInsuranceCompanyRepository carInsuranceCompanyRepository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private CarDetailsMapper carDetailsMapper;

    @Autowired
    private CarInsuranceCompanyMapper carInsuranceCompanyMapper;

    @Autowired
    private FuelTypeMapper fuelTypeMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public CarDetailsDto getCarDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        CarDetails carDetails = user.getCarDetails();
        return carDetails != null ? carDetailsMapper.toDto(carDetails) : new CarDetailsDto();
    }

    @Override
    public CarDetailsDto updateFuelType(Long userId, Long fuelTypeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        CarDetails carDetails = user.getCarDetails();
        if (carDetails == null) {
            carDetails = new CarDetails();
            user.setCarDetails(carDetails);
        }
        FuelType fuelType = fuelTypeRepository.findById(fuelTypeId).orElseThrow(() -> new ResourceNotFoundException("FuelType not found"));
        carDetails.setFuelType(fuelType);
        carDetailsRepository.save(carDetails);
        emailService.sendFuelParamInfoEmail(user);
        return carDetailsMapper.toDto(carDetails);
    }

    @Override
    public CarDetailsDto updateInsuranceCompany(Long userId, Long insuranceCompanyId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        CarDetails carDetails = user.getCarDetails();
        if (carDetails == null) {
            carDetails = new CarDetails();
            user.setCarDetails(carDetails);
        }
        CarInsuranceCompany insuranceCompany = carInsuranceCompanyRepository.findById(insuranceCompanyId)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance company not found"));
        carDetails.setInsuranceCompany(insuranceCompany);
        carDetailsRepository.save(carDetails);
        emailService.sendInsuranceChangedInfoEmail(user);
        return carDetailsMapper.toDto(carDetails);
    }

    @Override
    public CarDetailsDto updateLastMaintenanceDate(Long userId, LocalDate lastMaintenanceDate) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        CarDetails carDetails = user.getCarDetails();
        if (carDetails == null) {
            carDetails = new CarDetails();
            user.setCarDetails(carDetails);
        }
        carDetails.setLastMaintenanceDate(lastMaintenanceDate);
        carDetailsRepository.save(carDetails);
        emailService.sendMaintenanceDateChangedEmail(user);
        return carDetailsMapper.toDto(carDetails);
    }

    @Override
    public List<FuelTypeDto> getFuelTypes() {
        return fuelTypeRepository.findAll()
                .stream()
                .map(fuelTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarInsuranceCompanyDto> getAllInsuranceCompanies() {
        return carInsuranceCompanyRepository.findAll()
                .stream()
                .map(carInsuranceCompanyMapper::toDto)
                .collect(Collectors.toList());
    }
}