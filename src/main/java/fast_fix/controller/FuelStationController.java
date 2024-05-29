package fast_fix.controller;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.service.interfaces.FuelStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fuel-stations")
public class FuelStationController {

    private final FuelStationService service;

    public FuelStationController(FuelStationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public FuelStationDto[] getAllFuelStations() {
        return service.getAllFuelStations();
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<FuelStationDto>> getNearbyFuelStations(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<FuelStationDto> nearbyFuelStations = service.findNearby(latitude, longitude, radius);
        return ResponseEntity.ok(nearbyFuelStations);
    }
}
