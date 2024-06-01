package fast_fix.service;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.service.interfaces.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_BASE_URL = "https://api.tankerkoenig.de/json/list.php";

    @Override
    public List<FuelStationDto> getNearbyFuelStations(BigDecimal latitude, BigDecimal longitude, double radius, String type) {
        String url = String.format("%s?lat=%f&lng=%f&rad=%f&type=%s&apikey=%s", API_BASE_URL, latitude, longitude, radius, type, "your_api_key");
        FuelStationDto[] stations = restTemplate.getForObject(url, FuelStationDto[].class);
        return List.of(stations);
    }
}