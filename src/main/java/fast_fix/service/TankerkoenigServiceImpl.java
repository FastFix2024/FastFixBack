package fast_fix.service;

import fast_fix.config.TankerkoenigConfig;
import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.dto.FuelStationResponse;
import fast_fix.domain.dto.UserDto;
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

    private UserDto userDto;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<FuelStationDto> getStationsNearby(double lat, double lng, int rad, String fuelType) {
        String apiKey = tankerkoenigConfig.getApiKey();
        String url;

        if (fuelType == null || fuelType.isEmpty()) {
            url = String.format("https://creativecommons.tankerkoenig.de/json/list.php?lat=%f&lng=%f&rad=%d&type=all&apikey=%s",
                    lat, lng, rad, apiKey);
        } else {
            url = String.format("https://creativecommons.tankerkoenig.de/json/list.php?lat=%f&lng=%f&rad=%d&sort=price&type=%s&apikey=%s",
                    lat, lng, rad, fuelType, apiKey);
        }

        ResponseEntity<FuelStationResponse> response = restTemplate.getForEntity(url, FuelStationResponse.class);
        return response.getBody().getStations().stream()
                .map(fuelStationMapper::toDto)
                .collect(Collectors.toList());
    }
}