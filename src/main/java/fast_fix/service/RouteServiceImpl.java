package fast_fix.service;

import fast_fix.domain.dto.RouteDto;
import fast_fix.domain.entity.Route;
import fast_fix.domain.mapping.RouteMapper;
import fast_fix.repository.RouteRepository;
import fast_fix.service.interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public List<RouteDto> getAllRoutesByUser(Long userId) {
        List<Route> routes = routeRepository.findByUserId(userId);
        return routes.stream().map(routeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RouteDto createRoute(RouteDto routeDto) {
        Route route = routeMapper.toEntity(routeDto);
        Route savedRoute = routeRepository.save(route);
        return routeMapper.toDto(savedRoute);
    }

    @Override
    public RouteDto updateRoute(Long routeId, RouteDto routeDto) {
        Route existingRoute = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        existingRoute.setStartLocation(routeDto.getStartLocation());
        existingRoute.setEndLocation(routeDto.getEndLocation());
        existingRoute.setDuration(routeDto.getDuration());
        Route updatedRoute = routeRepository.save(existingRoute);
        return routeMapper.toDto(updatedRoute);
    }

    @Override
    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    @Override
    public RouteDto getRouteById(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        return routeMapper.toDto(route);
    }
}
