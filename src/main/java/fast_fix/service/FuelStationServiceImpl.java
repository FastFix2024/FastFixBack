package fast_fix.service;

import fast_fix.domain.dto.FuelStationDto;
import fast_fix.domain.entity.FuelStation;
import fast_fix.repository.FuelStationRepository;
import fast_fix.service.interfaces.FuelStationService;
import fast_fix.service.mapping.FuelStationMappingService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    private String fuelStationApiUrl = "https://creativecommons.tankerkoenig.de/json/list.php?lat=52.521&lng=13.438&rad=1.5&sort=dist&type=all&apikey=0bc4c8ca-21d0-ac93-2a74-4ae2a3afa795";
    private RestTemplate restTemplate;
    private FuelStationRepository fuelStationRepository;
    private FuelStationMappingService fuelStationMapper;

    public FuelStationServiceImpl(RestTemplate restTemplate, FuelStationRepository fuelStationRepository) {
        this.restTemplate = restTemplate;
        this.fuelStationRepository = fuelStationRepository;
        this.fuelStationMapper = FuelStationMappingService.INSTANCE;
    }

    @Override
    public FuelStationDto[] getAllFuelStations() {
        URI uri = UriComponentsBuilder.fromHttpUrl(fuelStationApiUrl)
                .build()
                .toUri();
        FuelStationDto[] fuelStations = restTemplate.getForObject(uri, FuelStationDto[].class);
        return fuelStations;
    }

    public List<FuelStationDto> getFuelStations(String location) {
        URI uri = UriComponentsBuilder.fromHttpUrl(fuelStationApiUrl)
                .queryParam("location", location)
                .build()
                .toUri();
        FuelStationDto[] fuelStations = restTemplate.getForObject(uri, FuelStationDto[].class);
        return Arrays.asList(fuelStations);
    }

    @Override
    public List<FuelStationDto> findNearby(double latitude, double longitude, double radius) {
        URI uri = UriComponentsBuilder.fromHttpUrl(fuelStationApiUrl)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("radius", radius)
                .build()
                .toUri();
        FuelStationDto[] fuelStations = restTemplate.getForObject(uri, FuelStationDto[].class);
        return Arrays.asList(fuelStations);
    }

    @Override
    public FuelStationDto save(FuelStationDto fuelStationDto) {
        FuelStation fuelStation = fuelStationMapper.toEntity(fuelStationDto);
        FuelStation savedStation = fuelStationRepository.save(fuelStation);
        return fuelStationMapper.toDto(savedStation);
    }
}
