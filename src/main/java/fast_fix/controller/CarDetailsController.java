package fast_fix.controller;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.dto.FuelStationDto;
import fast_fix.service.interfaces.CarDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car-details")
public class CarDetailsController {

    @Autowired
    private CarDetailsService carDetailsService;

    @Operation(summary = "Получить список настроек автомобиля(дата ТО, страховая, тип топлива)")
    @GetMapping("/{userId}")
    public ResponseEntity<CarDetailsDto> getCarDetails(@PathVariable Long userId) {
        CarDetailsDto carDetails = carDetailsService.getCarDetails(userId);
        return ResponseEntity.ok(carDetails);
    }

    @Operation(summary = "Обновить тип топлива пользователя")
    @PutMapping("/{userId}/fuel-type")
    public ResponseEntity<CarDetailsDto> updateFuelType(@PathVariable Long userId, @RequestBody String fuelType) {
        CarDetailsDto updatedCarDetails = carDetailsService.updateFuelType(userId, fuelType);
        return ResponseEntity.ok(updatedCarDetails);
    }

    @Operation(summary = "Обновить страховую компанию автомобиля пользователя")
    @PutMapping("/{userId}/insurance-company")
    public ResponseEntity<CarDetailsDto> updateInsuranceCompany(@PathVariable Long userId, @RequestBody Long insuranceCompanyId) {
        CarDetailsDto updatedCarDetails = carDetailsService.updateInsuranceCompany(userId, insuranceCompanyId);
        return ResponseEntity.ok(updatedCarDetails);
    }

    @Operation(summary = "Обновить дату последнего ТО")
    @PutMapping("/{userId}/last-maintenance-date")
    public ResponseEntity<CarDetailsDto> updateLastMaintenanceDate(@PathVariable Long userId, @RequestBody LocalDate lastMaintenanceDate) {
        CarDetailsDto updatedCarDetails = carDetailsService.updateLastMaintenanceDate(userId, lastMaintenanceDate);
        return ResponseEntity.ok(updatedCarDetails);
    }

    @Operation(summary = "Получить список типов топлива")
    @GetMapping("/fuel-types")
    public ResponseEntity<List<String>> getFuelTypes() {
        List<String> fuelTypes = carDetailsService.getFuelTypes();
        return ResponseEntity.ok(fuelTypes);
    }

    @Operation(summary = "Получить список заправок рядом с пользователем")
    @GetMapping("/stations")
    public ResponseEntity<List<FuelStationDto>> getStationsNearby(
            @RequestParam String fuelType,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam int radius) {
        List<FuelStationDto> stations = carDetailsService.getStationsNearby(fuelType, latitude, longitude, radius);
        return ResponseEntity.ok(stations);
    }

    @Operation(summary = "Получить список страховых компаний")
    @GetMapping("/insurance-companies")
    public ResponseEntity<List<CarInsuranceCompanyDto>> getAllInsuranceCompanies() {
        List<CarInsuranceCompanyDto> insuranceCompanies = carDetailsService.getAllInsuranceCompanies();
        return ResponseEntity.ok(insuranceCompanies);
    }
}