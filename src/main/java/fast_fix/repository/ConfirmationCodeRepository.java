package fast_fix.repository;

import fast_fix.domain.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    ConfirmationCode findByCode(String code);
}