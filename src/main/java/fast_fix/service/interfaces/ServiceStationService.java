package fast_fix.service.interfaces;

import fast_fix.domain.dto.ServiceStationDto;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceStationService {

    List<ServiceStationDto> getServiceStationsByLocation(BigDecimal latitude, BigDecimal longitude, double radius, String type);
//    ServiceStationDto getServiceStationDetails(String id);
}