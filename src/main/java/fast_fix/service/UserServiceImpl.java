package fast_fix.service;

import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.EmailService;
import fast_fix.service.interfaces.RoleService;
import fast_fix.service.interfaces.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;
    private BCryptPasswordEncoder encoder;
    private RoleService roleService;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(
            UserRepository repository,
            BCryptPasswordEncoder encoder,
            RoleService roleService,
            EmailService emailService
    ) {
        this.repository = repository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.emailService = emailService;
    }

    @Override
    public void registerUser(User user) throws MessagingException {
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.getRoleUser()));
        user.setActive(false);

        repository.save(user);
        emailService.sendConfirmationEmail(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

//    @Override
//    public User getById(Long id) {
//        return repository.getById(id);
//    }
//
//    @Override
//    public User save(User user) {
//        return repository.save(user);
//    }

//    @Override
//    public void registerUser(User user) throws MessagingException {
//        user.setActive(false);
//        repository.save(user);
//
//        String token = UUID.randomUUID().toString();
//        emailService.sendVerificationEmail(user, token);
//    }
}
