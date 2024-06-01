package fast_fix.controller;

import fast_fix.config.TestSecurityConfig;
import fast_fix.domain.dto.UserDto;
import fast_fix.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");
        userDto.setUsername("testuser");
        userDto.setActive(true);
    }

    @Test
    public void whenGetUserByEmail_thenReturnUserDTO() throws Exception {
        when(userService.findUserByEmail(userDto.getEmail())).thenReturn(userDto);

        mockMvc.perform(get("/api/users/{email}", userDto.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    public void whenRegisterUser_thenReturnUserDTO() throws Exception {
        when(userService.registerUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"username\":\"testuser\",\"isActive\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    public void whenDeleteUser_thenReturnNoContent() throws Exception {
        doNothing().when(userService).deleteUserById(userDto.getId());

        mockMvc.perform(delete("/api/users/{id}", userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}