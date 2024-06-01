package fast_fix.controller;

import fast_fix.domain.dto.RouteDto;
import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.service.interfaces.RouteService;
import fast_fix.service.interfaces.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RouteDto>> getAllRoutesByUser(@PathVariable Long userId) {
        List<RouteDto> routes = routeService.getAllRoutesByUser(userId);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto routeDto) {
        RouteDto createdRoute = routeService.createRoute(routeDto);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable Long routeId, @RequestBody RouteDto routeDto) {
        RouteDto updatedRoute = routeService.updateRoute(routeId, routeDto);
        return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long routeId) {
        routeService.deleteRoute(routeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long routeId) {
        RouteDto route = routeService.getRouteById(routeId);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }
}