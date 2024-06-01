package fast_fix.domain.mapping;

import fast_fix.domain.dto.CarDetailsDto;
import fast_fix.domain.entity.CarDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CarInsuranceCompanyMapper.class)
public interface CarDetailsMapper {

    CarDetailsDto toDto(CarDetails carDetails);

    CarDetails toEntity(CarDetailsDto carDetailsDto);
}