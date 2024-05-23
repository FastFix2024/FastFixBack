package fast_fix.service.interfaces;

import fast_fix.domain.entity.User;

public interface UserService {
    User getById(Long id);

    User save(User user);
}
