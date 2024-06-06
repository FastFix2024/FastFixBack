package fast_fix.repository;

import fast_fix.domain.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    ConfirmationCode findByCode(String code);
    @Modifying
    @Transactional
    @Query("DELETE FROM ConfirmationCode cc WHERE cc.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}