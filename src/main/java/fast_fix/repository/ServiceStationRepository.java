package fast_fix.repository;

import fast_fix.domain.entity.ServiceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceStationRepository extends JpaRepository<ServiceStation, Long> {
}