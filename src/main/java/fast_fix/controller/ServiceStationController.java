package fast_fix.controller;

import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.exceptions.ApiRequestException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.service.interfaces.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/service-stations")
public class ServiceStationController {

    @Autowired
    private ServiceStationService serviceStationService;

    @GetMapping("/nearby")
    public ResponseEntity<List<ServiceStationDto>> getServiceStationsByLocation(@RequestParam BigDecimal lat, @RequestParam BigDecimal lng, @RequestParam double rad, @RequestParam String type) {
        try {
            List<ServiceStationDto> stations = serviceStationService.getServiceStationsByLocation(lat, lng, rad, type);
            return ResponseEntity.ok(stations);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ApiRequestException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/{id}")
//    public ServiceStationDto getServiceStationDetails(@PathVariable String id) {
//        return serviceStationService.getServiceStationDetails(id);
//    }
}