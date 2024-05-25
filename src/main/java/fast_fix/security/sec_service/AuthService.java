package fast_fix.security.sec_service;

import fast_fix.domain.entity.User;
import fast_fix.security.AuthInfo;
import fast_fix.security.sec_dto.TokenResponseDto;
import fast_fix.service.interfaces.UserService;
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

    private UserService userService;
    private TokenService tokenService;
    private Map<String, String> refreshStorage;
    private BCryptPasswordEncoder encoder;

    public AuthService(
            UserService userService,
            TokenService tokenService,
            BCryptPasswordEncoder encoder
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(@NonNull User inboundUser) throws AuthException {
        String username = inboundUser.getUsername();
        User foundUser = (User) userService.loadUserByUsername(username);

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())){
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(username, refreshToken);
            return new TokenResponseDto(accessToken, refreshToken);
        }else {
            throw new AuthException("Password is incorrect");
        }
    }
    public TokenResponseDto getAccessToken(@NonNull String inboundRefreshToken){
        Claims refreshClaims = tokenService.getRefrashClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String savedRefreshToken = refreshStorage.get(username);

        if (inboundRefreshToken.equals(savedRefreshToken)){
            // или так: if (savedRefreshToken != null && savedRefreshToken.equals(inboundRefreshToken)){
            User user = (User) userService.loadUserByUsername(username);
            String accessToken = tokenService.generateAccessToken(user);
            return new TokenResponseDto(accessToken, null);
        }
        return new TokenResponseDto(null,null);
    }

//    если нужна будет инфа о пользователе из самого SpringSecurity
//public AuthInfo getAuthInfo(){
//    return (AuthInfo) SecurityContextHolder.getContext().getAuthentication();
//}
}
