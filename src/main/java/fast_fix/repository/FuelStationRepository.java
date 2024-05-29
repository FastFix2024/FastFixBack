package fast_fix.repository;

import fast_fix.domain.entity.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {
}
