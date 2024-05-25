package fast_fix.service.mapping;

import fast_fix.domain.dto.CustomerDto;
import fast_fix.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMappingService {

    CustomerDto mapEntityToDto(Customer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Customer mapDtoToEntity(CustomerDto dto);
}
