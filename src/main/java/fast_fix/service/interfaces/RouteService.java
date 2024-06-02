package fast_fix.service.interfaces;

import fast_fix.domain.dto.RouteDto;

import java.util.List;

public interface RouteService {
    RouteDto getRouteById(Long id);
    RouteDto createRoute(RouteDto routeDto);
    RouteDto updateRoute(Long id, RouteDto routeDto);
    void deleteRoute(Long id);
    List<RouteDto> getRoutesFromAPI(String startLocation, String endLocation);
}