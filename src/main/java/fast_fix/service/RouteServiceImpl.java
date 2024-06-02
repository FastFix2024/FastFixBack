package fast_fix.service;

import fast_fix.domain.dto.RouteDto;
import fast_fix.domain.dto.GoogleDirectionsResponse;
import fast_fix.domain.entity.Route;
import fast_fix.exceptions.ApiRequestException;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.domain.mapping.RouteMapper;
import fast_fix.repository.RouteRepository;
import fast_fix.service.interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fast_fix.config.ApiKeyConfig;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyConfig apiKeyConfig;

    private static final String ROUTE_API_URL = "https://maps.googleapis.com/maps/api/directions/json";

    @Override
    public RouteDto getRouteById(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + id));
        return routeMapper.toDto(route);
    }

    @Override
    public RouteDto createRoute(RouteDto routeDto) {
        Route route = routeMapper.toEntity(routeDto);
        route = routeRepository.save(route);
        return routeMapper.toDto(route);
    }

    @Override
    public RouteDto updateRoute(Long id, RouteDto routeDto) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + id));
        route.setStartLocation(routeDto.getStartLocation());
        route.setEndLocation(routeDto.getEndLocation());
        route.setDuration(routeDto.getDuration());
        route.setDistance(routeDto.getDistance());
        route = routeRepository.save(route);
        return routeMapper.toDto(route);
    }

    @Override
    public void deleteRoute(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + id));
        routeRepository.delete(route);
    }

    @Override
    public List<RouteDto> getRoutesFromAPI(String startLocation, String endLocation) {
        String url = String.format("%s?origin=%s&destination=%s&key=%s",
                ROUTE_API_URL, startLocation, endLocation, apiKeyConfig.getGoogleApiKey());
        try {
            GoogleDirectionsResponse response = restTemplate.getForObject(url, GoogleDirectionsResponse.class);
            return response.getRoutes().stream()
                    .map(routeMapper::googleRouteToRouteDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ApiRequestException("Error fetching routes from API", e);
        }
    }
}
