package fast_fix.controller;

import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.service.interfaces.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/service-stations")
public class ServiceStationController {

    @Autowired
    private ServiceStationService serviceStationService;

    @GetMapping
    public List<ServiceStationDto> getServiceStationsByLocation(@RequestParam BigDecimal lat, @RequestParam BigDecimal lng, @RequestParam double rad, @RequestParam String type) {
        return serviceStationService.getServiceStationsByLocation(lat, lng, rad, type);
    }

    @GetMapping("/{id}")
    public ServiceStationDto getServiceStationDetails(@PathVariable String id) {
        return serviceStationService.getServiceStationDetails(id);
    }
}