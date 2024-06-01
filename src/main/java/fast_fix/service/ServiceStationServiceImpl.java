package fast_fix.service;

import fast_fix.domain.dto.ServiceStationDto;
import fast_fix.service.interfaces.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import fast_fix.config.ApiKeyConfig;
import org.springframework.stereotype.Service;

@Service
public class ServiceStationServiceImpl implements ServiceStationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyConfig apiKeyConfig;

    private static final String API_BASE_URL = "https://external-api.example.com/service-stations";

    @Override
    public List<ServiceStationDto> getServiceStationsByLocation(BigDecimal latitude, BigDecimal longitude, double radius) {
        String url = String.format("%s?lat=%f&lng=%f&radius=%f&key=%s", API_BASE_URL, latitude, longitude, radius, apiKeyConfig.getGoogleApiKey());
        ServiceStationDto[] stations = restTemplate.getForObject(url, ServiceStationDto[].class);
        return List.of(stations);
    }

    @Override
    public ServiceStationDto getServiceStationDetails(Long id) {
        String url = String.format("%s/%d?key=%s", API_BASE_URL, id, apiKeyConfig.getGoogleApiKey());
        return restTemplate.getForObject(url, ServiceStationDto.class);
    }
}