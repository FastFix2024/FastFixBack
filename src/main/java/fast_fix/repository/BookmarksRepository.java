package fast_fix.repository;

import fast_fix.domain.entity.Bookmarks;
import fast_fix.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarksRepository extends JpaRepository<Bookmarks, Long> {

    List<Bookmarks> findByUser(User user);
}
