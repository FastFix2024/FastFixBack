package fast_fix.repository;

import fast_fix.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setUsername("testuser");  // Устанавливаем username
        user.setActive(true);        // Устанавливаем статус активности
        entityManager.persistAndFlush(user);
    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        User found = userRepository.findByEmail(user.getEmail());
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void whenFindById_thenReturnUser() {
        User found = userRepository.findById(user.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(user.getId());
    }

    @Test
    public void whenSave_thenReturnUser() {
        User newUser = new User();
        newUser.setEmail("new@example.com");
        newUser.setPassword("newpassword");
        newUser.setUsername("newuser");  // Устанавливаем username
        newUser.setActive(true);       // Устанавливаем статус активности
        userRepository.save(newUser);

        User found = userRepository.findByEmail("new@example.com");
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("new@example.com");
    }

    @Test
    public void whenDeleteById_thenUserShouldBeDeleted() {
        userRepository.deleteById(user.getId());
        User found = userRepository.findById(user.getId()).orElse(null);
        assertThat(found).isNull();
    }
}
