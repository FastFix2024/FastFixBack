package fast_fix.service.mapping;

import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
