package fast_fix.repository;

import fast_fix.domain.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {

        InsuranceCompany findById(long id);
}
