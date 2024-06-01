package fast_fix.service.interfaces;

import fast_fix.domain.dto.RouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> getAllRoutesByUser(Long userId);
    RouteDto createRoute(RouteDto routeDto);
    RouteDto updateRoute(Long routeId, RouteDto routeDto);
    void deleteRoute(Long routeId);
    RouteDto getRouteById(Long routeId);
}