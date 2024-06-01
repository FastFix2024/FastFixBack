package fast_fix.domain.mapping;

import fast_fix.domain.dto.EmergencyContactDto;
import fast_fix.domain.entity.EmergencyContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {

    EmergencyContactDto toDto(EmergencyContact emergencyContact);

    EmergencyContact toEntity(EmergencyContactDto emergencyContactDto);
}