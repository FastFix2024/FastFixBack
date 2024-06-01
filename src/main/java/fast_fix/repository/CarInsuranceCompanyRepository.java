package fast_fix.repository;

import fast_fix.domain.entity.CarInsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInsuranceCompanyRepository extends JpaRepository<CarInsuranceCompany, Integer> {
}