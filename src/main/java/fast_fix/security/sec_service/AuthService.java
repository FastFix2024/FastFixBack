package fast_fix.security.sec_service;

import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.security.AuthInfo;
import fast_fix.security.sec_dto.TokenResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder encoder;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public AuthService(UserRepository userRepository, TokenService tokenService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.encoder = encoder;
    }

    public TokenResponseDto login(@NonNull User inboundUser) throws AuthException {
        String email = inboundUser.getEmail();
        User foundUser = userRepository.findUserByEmail(email);

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(email, refreshToken);

            return new TokenResponseDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }

    public TokenResponseDto getAccessToken(@NonNull String inboundRefreshToken) {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String email = refreshClaims.getSubject();
        String savedRefreshToken = refreshStorage.get(email);

        if (inboundRefreshToken.equals(savedRefreshToken)) {
            User user = userRepository.findUserByEmail(email);
            String accessToken = tokenService.generateAccessToken(user);

            return new TokenResponseDto(accessToken, null);
        }
        return new TokenResponseDto(null, null);
    }

    public AuthInfo getAuthInfo() {
        return (AuthInfo) SecurityContextHolder.getContext().getAuthentication();
    }
}