package fast_fix.controller;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.service.interfaces.CarDetailsService;
import fast_fix.service.interfaces.TankerkoenigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "CarDetails controller")
@RestController
@RequestMapping("/car-details")
public class CarDetailsController {

    @Autowired
    private CarDetailsService carDetailsService;

    @Autowired
    private TankerkoenigService tankerkoenigService;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    @Operation(summary = "Получить список настроек автомобиля(дата ТО, страховая, тип топлива)")
    @GetMapping("/{userId}")
    public ResponseEntity<CarDetailsDto> getCarDetails(@PathVariable Long userId) {
        if (!isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CarDetailsDto carDetails = carDetailsService.getCarDetails(userId);
        return ResponseEntity.ok(carDetails);
    }

    @Operation(summary = "Обновить тип топлива пользователя")
    @PutMapping("/{userId}/fuel-type")
    public ResponseEntity<CarDetailsDto> updateFuelType(@PathVariable Long userId, @RequestBody String fuelType) {
        if (!isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CarDetailsDto updatedCarDetails = carDetailsService.updateFuelType(userId, fuelType);
        return ResponseEntity.ok(updatedCarDetails);
    }

    @Operation(summary = "Обновить страховую компанию автомобиля пользователя")
    @PutMapping("/{userId}/insurance-company")
    public ResponseEntity<CarDetailsDto> updateInsuranceCompany(@PathVariable Long userId, @RequestBody Long insuranceCompanyId) {
        if (!isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CarDetailsDto updatedCarDetails = carDetailsService.updateInsuranceCompany(userId, insuranceCompanyId);
        return ResponseEntity.ok(updatedCarDetails);
    }

    @Operation(summary = "Обновить дату последнего ТО")
    @PutMapping("/{userId}/last-maintenance-date")
    public ResponseEntity<CarDetailsDto> updateLastMaintenanceDate(@PathVariable Long userId, @RequestBody LocalDate lastMaintenanceDate) {
        if (!isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CarDetailsDto updatedCarDetails = carDetailsService.updateLastMaintenanceDate(userId, lastMaintenanceDate);
        return ResponseEntity.ok(updatedCarDetails);
    }

    @Operation(summary = "Получить список типов топлива")
    @GetMapping("/fuel-types")
    public ResponseEntity<List<String>> getFuelTypes() {
        if (!isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<String> fuelTypes = carDetailsService.getFuelTypes();
        return ResponseEntity.ok(fuelTypes);
    }

    @Operation(summary = "Получить список заправок рядом с пользователем")
    @GetMapping("/stations")
    public ResponseEntity<Map<String, Object>> getStationsNearby(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam int radius,
            Principal principal) {
        if (principal == null || !isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String fuelType = null;
        Long userId = getCurrentUserId(principal);
        if (userId != null) {
            CarDetailsDto carDetails = carDetailsService.getCarDetails(userId);
            fuelType = carDetails.getFuelType();
        }

        List<FuelStationDto> stations = tankerkoenigService.getStationsNearby(latitude, longitude, radius, fuelType);

        // Создаем JSON объект для ответа
        Map<String, Object> response = new HashMap<>();
        response.put("latitude", latitude);
        response.put("longitude", longitude);
        response.put("radius", radius);
        response.put("fuelType", fuelType);
        response.put("stations", stations);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Получить список страховых компаний")
    @GetMapping("/insurance-companies")
    public ResponseEntity<List<CarInsuranceCompanyDto>> getAllInsuranceCompanies() {
        if (!isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<CarInsuranceCompanyDto> insuranceCompanies = carDetailsService.getAllInsuranceCompanies();
        return ResponseEntity.ok(insuranceCompanies);
    }

    private Long getCurrentUserId(Principal principal) {
        if (principal == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principalObject = authentication.getPrincipal();
            if (principalObject instanceof UserDto) {
                UserDto userDto = (UserDto) principalObject;
                return userDto.getId();
            }
        }
        return null;
    }
}