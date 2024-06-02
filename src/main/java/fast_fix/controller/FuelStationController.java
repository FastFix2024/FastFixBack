package fast_fix.controller;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.exceptions.ApiRequestException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.service.interfaces.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fuel-stations")
public class FuelStationController {

    @Autowired
    private FuelStationService fuelStationService;

    @GetMapping
    public ResponseEntity<List<FuelStationDto>> getNearbyFuelStations(
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude,
            @RequestParam double radius,
            @RequestParam String type) {
        try {
            List<FuelStationDto> stations = fuelStationService.getFuelStationsByLocation(latitude, longitude, radius, type);
            return ResponseEntity.ok(stations);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ApiRequestException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}