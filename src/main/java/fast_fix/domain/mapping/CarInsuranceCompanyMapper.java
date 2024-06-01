package fast_fix.domain.mapping;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.domain.entity.CarDetails;
import fast_fix.domain.entity.CarInsuranceCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CarDetails.class)
public interface CarInsuranceCompanyMapper {

    CarInsuranceCompanyDto toDto(CarInsuranceCompany carInsuranceCompany);

    CarInsuranceCompany toEntity(CarInsuranceCompanyDto carInsuranceCompanyDto);
}