package fast_fix.repository;

import fast_fix.domain.entity.Bookmark;
import fast_fix.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUser(User user);
}
