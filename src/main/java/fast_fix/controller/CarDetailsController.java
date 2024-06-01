package fast_fix.controller;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.service.interfaces.CarDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-details")
public class CarDetailsController {

    @Autowired
    private CarDetailsService carDetailsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CarDetailsDto> getCarDetailsByUserId(@PathVariable Long userId) {
        CarDetailsDto carDetails = carDetailsService.getCarDetailsByUserId(userId);
        if (carDetails != null) {
            return ResponseEntity.ok(carDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<CarDetailsDto> updateCarDetails(@PathVariable Long userId, @RequestBody CarDetailsDto carDetailsDto) {
        CarDetailsDto updatedCarDetails = carDetailsService.updateCarDetails(userId, carDetailsDto);
        if (updatedCarDetails != null) {
            return ResponseEntity.ok(updatedCarDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}