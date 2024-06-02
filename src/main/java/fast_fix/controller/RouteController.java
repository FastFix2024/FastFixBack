package fast_fix.controller;

import fast_fix.domain.dto.RouteDto;
import fast_fix.exceptions.ApiRequestException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.service.interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        try {
            RouteDto route = routeService.getRouteById(id);
            return ResponseEntity.ok(route);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto routeDto) {
        RouteDto createdRoute = routeService.createRoute(routeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable Long id, @RequestBody RouteDto routeDto) {
        try {
            RouteDto updatedRoute = routeService.updateRoute(id, routeDto);
            return ResponseEntity.ok(updatedRoute);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        try {
            routeService.deleteRoute(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/from-api")
    public ResponseEntity<List<RouteDto>> getRoutesFromAPI(@RequestParam String startLocation, @RequestParam String endLocation) {
        try {
            List<RouteDto> routes = routeService.getRoutesFromAPI(startLocation, endLocation);
            return ResponseEntity.ok(routes);
        } catch (ApiRequestException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}