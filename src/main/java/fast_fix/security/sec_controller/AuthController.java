package fast_fix.security.sec_controller;

import fast_fix.domain.entity.User;
import fast_fix.security.sec_dto.TokenResponseDto;
import fast_fix.security.sec_service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication controller")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Залогинить пользователя")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response) {
        try {
            TokenResponseDto responseDto = authService.login(user);
            Cookie accessTokenCookie = new Cookie("Access-Token", responseDto.getAccessToken());
            accessTokenCookie.setPath("/");
            accessTokenCookie.setHttpOnly(true);
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = new Cookie("Refresh-Token", responseDto.getRefreshToken());
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setHttpOnly(true);
            response.addCookie(refreshTokenCookie);

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Получить новые access token и refresh token")
    @PostMapping("/access")
    public ResponseEntity<Object> getNewAccessToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String refreshToken = getTokenFromCookies(request, "Refresh-Token");
            if (refreshToken == null) {
                return new ResponseEntity<>("Refresh token is missing", HttpStatus.BAD_REQUEST);
            }

            TokenResponseDto responseDto = authService.getAccessToken(refreshToken);
            if (responseDto.getAccessToken() != null) {
                Cookie accessTokenCookie = new Cookie("Access-Token", responseDto.getAccessToken());
                accessTokenCookie.setPath("/");
                accessTokenCookie.setHttpOnly(true);
                response.addCookie(accessTokenCookie);

                return ResponseEntity.ok(responseDto);
            } else {
                return new ResponseEntity<>("Invalid refresh token", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Выйти из системы")
    @GetMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("Access-Token", null);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setMaxAge(0);
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("Refresh-Token", null);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setMaxAge(0);
        response.addCookie(refreshTokenCookie);
    }

    private String getTokenFromCookies(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}