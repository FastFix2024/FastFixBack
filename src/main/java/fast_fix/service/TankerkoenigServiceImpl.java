package fast_fix.service;

import fast_fix.config.TankerkoenigConfig;
import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import fast_fix.mapping.FuelStationMapper;
import fast_fix.service.interfaces.TankerkoenigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TankerkoenigServiceImpl implements TankerkoenigService {

    @Autowired
    private TankerkoenigConfig tankerkoenigConfig;

    @Autowired
    private FuelStationMapper fuelStationMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final List<String> FUEL_TYPES = List.of("E5", "E10", "Diesel", "Super", "Super Plus", "LPG", "CNG", "Bio Diesel", "Bio Ethanol", "Electric");
    @Override
    public List<String> getFuelTypes() {
        return FUEL_TYPES;
    }

    @Override
    public List<FuelStationDto> getStationsNearby(String fuelType, double latitude, double longitude, int radius) {
        String apiKey = tankerkoenigConfig.getApiKey();
        String url = String.format("https://creativecommons.tankerkoenig.de/json/list.php?lat=%f&lng=%f&rad=%d&sort=price&type=%s&apikey=%s",
                latitude, longitude, radius, fuelType, apiKey);
        ResponseEntity<FuelStationResponse> response = restTemplate.getForEntity(url, FuelStationResponse.class);
        return response.getBody().getStations().stream()
                .map(fuelStationMapper::toDto)
                .collect(Collectors.toList());
    }
}
