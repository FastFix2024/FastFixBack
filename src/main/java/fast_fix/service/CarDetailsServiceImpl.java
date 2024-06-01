package fast_fix.service;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.entity.CarDetails;
import fast_fix.domain.entity.User;
import fast_fix.domain.mapping.CarDetailsMapper;
import fast_fix.domain.mapping.CarInsuranceCompanyMapper;
import fast_fix.exception_handling.exceptions.UserNotFoundException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.repository.CarDetailsRepository;
import fast_fix.repository.CarInsuranceCompanyRepository;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.CarDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarDetailsServiceImpl implements CarDetailsService {

    private CarDetailsRepository carDetailsRepository;

    private CarDetailsMapper carDetailsMapper;

    private CarInsuranceCompanyRepository carInsuranceCompanyRepository;

    private CarInsuranceCompanyMapper carInsuranceCompanyMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CarDetailsDto getCarDetailsByUserId(Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
            CarDetails carDetails = user.getCarDetails();
            return carDetailsMapper.toDto(carDetails);
    }

    @Override
    public CarDetailsDto updateCarDetails(Long userId, CarDetailsDto carDetailsDto) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
            CarDetails carDetails = user.getCarDetails();
            carDetails.setFuelType(carDetailsDto.getFuelType());
            carDetails.setLastMaintenanceDate(carDetailsDto.getLastMaintenanceDate());
            carDetails.setInsuranceCompany(carInsuranceCompanyMapper.toEntity(carDetailsDto.getInsuranceCompany()));
            carDetails = carDetailsRepository.save(carDetails);
            return carDetailsMapper.toDto(carDetails);
    }
}