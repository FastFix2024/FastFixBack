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
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam double rad) {
        List<FuelStationDto> nearbyFuelStations = service.findNearby(lat, lng, rad);
        return ResponseEntity.ok(nearbyFuelStations);
    }
}
