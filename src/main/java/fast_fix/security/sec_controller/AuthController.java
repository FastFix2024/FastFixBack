package fast_fix.security.sec_controller;

import fast_fix.domain.dto.UserDto;
import fast_fix.domain.entity.User;
import fast_fix.security.VerificationToken;
import fast_fix.security.sec_dto.*;
import fast_fix.security.sec_service.AuthService;
import fast_fix.security.sec_service.TokenService;
import fast_fix.service.interfaces.UserService;
import fast_fix.service.mapping.UserMappingService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final TokenService tokenService;
    private final UserMappingService userMappingService;

    public AuthController(AuthService authService, UserService userService, TokenService tokenService, UserMappingService userMappingService) {
        this.authService = authService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMappingService = userMappingService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
        User user = userMappingService.userDtoToUser(userDto);
        user.setPassword(userService.encodePassword(userDto.getPassword()));
        userService.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDto dto, HttpServletResponse response){
        try {
            User user = userMappingService.userDtoToUser(dto);
            TokenResponseDto tokenDto = authService.login(user);
            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/access")
    public ResponseEntity<Object> getNewAccessToken(@RequestBody RefreshRequestDto request, HttpServletResponse response){
        try {
            TokenResponseDto tokenDto = authService.getAccessToken(request.getRefreshToken());
            Cookie cookie = new Cookie("Access-Token", tokenDto.getAccessToken());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("Access-Token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody PasswordChangeRequestDto request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        if (userDetails == null || !(userDetails instanceof User)) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
        User user = (User) userDetails;

        if (!userService.checkPassword(user, request.getOldPassword())) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }

        userService.changePassword(user, request.getNewPassword());
        return ResponseEntity.ok("Password successfully changed");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        if (userDetails == null || !(userDetails instanceof User)) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        User user = (User) userDetails;

        String token = tokenService.generateVerificationToken(user);

        // Отправьте токен пользователю (например, по электронной почте)
        // emailService.sendPasswordResetToken(user.getEmail(), token);

        return ResponseEntity.ok("Password reset token sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody PasswordResetRequestDto request) {
        VerificationToken verificationToken = tokenService.findByToken(request.getToken());
        if (verificationToken == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }
        User user = verificationToken.getUser();

        userService.changePassword(user, request.getNewPassword());
        tokenService.deleteVerificationToken(verificationToken);

        return ResponseEntity.ok("Password successfully reset");
    }
}



