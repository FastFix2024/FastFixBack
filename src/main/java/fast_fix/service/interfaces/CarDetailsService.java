package fast_fix.service.interfaces;

import fast_fix.domain.dto.CarDetailsDto;

public interface CarDetailsService {
    CarDetailsDto getCarDetailsByUserId(Long userId);
    CarDetailsDto updateCarDetails(Long userId, CarDetailsDto carDetailsDto);
}