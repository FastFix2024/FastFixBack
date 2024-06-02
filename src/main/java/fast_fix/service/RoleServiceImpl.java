package fast_fix.service;

import fast_fix.domain.entity.Role;
import fast_fix.repository.RoleRepository;
import fast_fix.service.interfaces.RoleService;

public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleUser() {
        Role role = repository.findByTitle("ROLE_USER");

        if (role == null) {
            throw new RuntimeException("Database doesn't contain ROLE_USER");
        }

        return role;
    }
}