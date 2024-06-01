package fast_fix.service;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import fast_fix.domain.mapping.UserMapper;
import fast_fix.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setUsername("testuser");
        user.setActive(true);

        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setUsername("testuser");
        userDto.setActive(true);
    }

    @Test
    public void whenFindUserByEmail_thenReturnUserDTO() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto found = userService.findUserByEmail(user.getEmail());

        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void whenCreateUser_thenReturnUserDTO() {
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto created = userService.createUser(userDto);

        assertThat(created).isNotNull();
        assertThat(created.getEmail()).isEqualTo(user.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void whenDeleteUser_thenRepositoryDeleteShouldBeCalled() {
        doNothing().when(userRepository).deleteById(user.getId());

        userService.deleteUserById(user.getId());

        verify(userRepository, times(1)).deleteById(user.getId());
    }
}