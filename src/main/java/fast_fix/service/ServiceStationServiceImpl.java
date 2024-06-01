package fast_fix.service;

import fast_fix.domain.dto.GooglePlaceResponse;
import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.domain.mapping.ServiceStationMapper;
import fast_fix.service.interfaces.ServiceStationService;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import fast_fix.config.ApiKeyConfig;
import org.springframework.stereotype.Service;

@Service
public class ServiceStationServiceImpl implements ServiceStationService {

    private RestTemplate restTemplate;

    private ApiKeyConfig apiKeyConfig;

    private ServiceStationMapper serviceStationMapper;

    private static final String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final String PLACE_DETAILS_API_URL = "https://maps.googleapis.com/maps/api/place/details/json";

    @Override
    public List<ServiceStationDto> getServiceStationsByLocation(BigDecimal latitude, BigDecimal longitude, double radius, String type) {
        String url = String.format("%s?location=%f,%f&radius=%f&type=%s&key=%s",
                PLACES_API_URL,
                latitude.doubleValue(),
                longitude.doubleValue(),
                radius,
                type,
                apiKeyConfig.getGoogleApiKey());
        GooglePlaceResponse response = restTemplate.getForObject(url, GooglePlaceResponse.class);
        return response.getResults().stream()
                .map(serviceStationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceStationDto getServiceStationDetails(String id) {
        String url = String.format("%s?place_id=%s&key=%s",
                PLACE_DETAILS_API_URL,
                id,
                apiKeyConfig.getGoogleApiKey());
        return restTemplate.getForObject(url, ServiceStationDto.class);
    }
}