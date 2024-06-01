package fast_fix.service;

import fast_fix.domain.dto.GooglePlaceResponse;
import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.service.interfaces.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import fast_fix.config.ApiKeyConfig;
import org.springframework.stereotype.Service;

@Service
public class ServiceStationServiceImpl implements ServiceStationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyConfig apiKeyConfig;

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
        return response.getResults().stream().map(this::mapToServiceStationDto).collect(Collectors.toList());
    }

    private ServiceStationDto mapToServiceStationDto(GooglePlaceResponse.Result result) {
        ServiceStationDto dto = new ServiceStationDto();
        dto.setId(result.getPlaceId());
        dto.setName(result.getName());
        dto.setAddress(result.getVicinity());
        dto.setLatitude(result.getGeometry().getLocation().getLat());
        dto.setLongitude(result.getGeometry().getLocation().getLng());
        return dto;
    }

    @Override
    public ServiceStationDto getServiceStationDetails(String id) {
        String url = String.format("%s?place_id=%d&key=%s",
                PLACE_DETAILS_API_URL,
                id,
                apiKeyConfig.getGoogleApiKey());
        return restTemplate.getForObject(url, ServiceStationDto.class);
    }
}