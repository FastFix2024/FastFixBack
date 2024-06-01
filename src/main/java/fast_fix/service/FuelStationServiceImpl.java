package fast_fix.service;

import fast_fix.config.ApiKeyConfig;
import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import fast_fix.domain.mapping.FuelStationMapper;
import fast_fix.service.interfaces.FuelStationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    private RestTemplate restTemplate;

    private ApiKeyConfig apiConfig;

    private FuelStationMapper fuelStationMapper;

    private static final String API_BASE_URL = "https://creativecommons.tankerkoenig.de/json/list.php";

    @Override
    public List<FuelStationDto> getFuelStationsByLocation(BigDecimal latitude, BigDecimal longitude, double radius, String fuelType) {
        String url = String.format("%s?lat=%f&lng=%f&rad=%f&sort=dist&type=%s&apikey=%s",
                API_BASE_URL,
                latitude,
                longitude,
                radius,
                fuelType,
                apiConfig.getTankerkoenigApiKey());
        FuelStationResponse response = restTemplate.getForObject(url, FuelStationResponse.class);
        return response.getStations().stream()
                .map(fuelStationMapper::toDto)
                .collect(Collectors.toList());
    }
}