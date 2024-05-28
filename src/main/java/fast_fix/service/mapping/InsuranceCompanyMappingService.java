package fast_fix.service.mapping;

import fast_fix.domain.dto.InsuranceCompanyDto;
import fast_fix.domain.entity.InsuranceCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsuranceCompanyMappingService {

    InsuranceCompanyDto insCompToInsCompDto(InsuranceCompany insComp);

    @Mapping(target = "id", ignore = true)
    InsuranceCompany insCompDtoToInsComp(InsuranceCompanyDto insCompDto);
}
