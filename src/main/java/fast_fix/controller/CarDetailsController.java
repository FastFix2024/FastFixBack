package fast_fix.controller;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.service.interfaces.CarDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car-details")
public class CarDetailsController {

    @Autowired
    private CarDetailsService carDetailsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CarDetailsDto> getCarDetailsByUserId(@PathVariable Long userId) {
        try {
            CarDetailsDto carDetails = carDetailsService.getCarDetailsByUserId(userId);
            return ResponseEntity.ok(carDetails);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<CarDetailsDto> updateCarDetails(@PathVariable Long userId, @RequestBody CarDetailsDto carDetailsDto) {
        try {
            CarDetailsDto updatedCarDetails = carDetailsService.updateCarDetails(userId, carDetailsDto);
            return ResponseEntity.ok(updatedCarDetails);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}