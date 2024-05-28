package fast_fix.configuration;

import fast_fix.domain.dto.InsuranceCompanyDto;
import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.InsuranceCompany;
import fast_fix.domain.entity.User;
import fast_fix.service.mapping.InsuranceCompanyMappingService;
import fast_fix.service.mapping.UserMappingService;
import fast_fix.service.mapping.UserMappingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserMappingService userMappingService() {
        return new UserMappingServiceImpl();
    }

    @Bean
    public InsuranceCompanyMappingService insuranceCompanyMappingService() {
        return new InsuranceCompanyMappingService() {
            @Override
            public InsuranceCompanyDto insCompToInsCompDto(InsuranceCompany insComp) {
                if (insComp == null) {
                    return null;
                }
                InsuranceCompanyDto dto = new InsuranceCompanyDto();
                dto.setId(insComp.getId());
                dto.setCompanyName(insComp.getCompanyName());
                dto.setPhone(insComp.getPhone());
                dto.setWebsite(insComp.getWebsite());
                return dto;
            }

            @Override
            public InsuranceCompany insCompDtoToInsComp(InsuranceCompanyDto insCompDto) {
                if (insCompDto == null) {
                    return null;
                }
                InsuranceCompany entity = new InsuranceCompany();
                entity.setId(insCompDto.getId());
                entity.setCompanyName(insCompDto.getCompanyName());
                entity.setPhone(insCompDto.getPhone());
                entity.setWebsite(insCompDto.getWebsite());
                return entity;
            }
        };
    }
}