package fast_fix.mapping;

import fast_fix.domain.dto.FuelTypeDto;
import fast_fix.domain.entity.FuelType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelTypeMapper {
    FuelTypeDto toDto(FuelType fuelType);

    FuelType toEntity(FuelTypeDto fuelTypeDto);
}
