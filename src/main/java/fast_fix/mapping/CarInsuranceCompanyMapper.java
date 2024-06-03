package fast_fix.mapping;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.entity.CarInsuranceCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarInsuranceCompanyMapper {
    CarInsuranceCompanyDto toDto(CarInsuranceCompany carInsuranceCompany);
    CarInsuranceCompany toEntity(CarInsuranceCompanyDto carInsuranceCompanyDto);
}