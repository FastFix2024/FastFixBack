package fast_fix.security.sec_service;

import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.security.AuthInfo;
import fast_fix.security.sec_dto.TokenResponseDto;
import fast_fix.service.interfaces.EmailService;
import fast_fix.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;
    private final Map<String, String> refreshStorage;

    public AuthService(UserService userService, UserRepository userRepository, TokenService tokenService, BCryptPasswordEncoder encoder, EmailService emailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.emailService = emailService;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(@NonNull User inboundUser) throws AuthException {
        String email = inboundUser.getEmail();
        User foundUser = (User) userService.loadUserByUsername(email);

        if (encoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(email, refreshToken);

            return new TokenResponseDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Email or password is incorrect");
        }
    }

    public TokenResponseDto getAccessToken(@NonNull String inboundRefreshToken) {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String savedRefreshToken = refreshStorage.get(username);

        if (inboundRefreshToken.equals(savedRefreshToken)) {
            User user = (User) userService.loadUserByUsername(username);
            String accessToken = tokenService.generateAccessToken(user);

            return new TokenResponseDto(accessToken, null);
        }
        return new TokenResponseDto(null, null);
    }

    public AuthInfo getAuthInfo() {
        return (AuthInfo) SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean resetPassword(String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return false;
        }

        String newPassword = generateRandomPassword();
        user.setPassword(encoder.encode(newPassword));
        userService.save(user);

        emailService.sendResetPasswordEmail(user, newPassword);
        return true;
    }

    private String generateRandomPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true); // 2 заглавные буквы
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true); // 2 строчные буквы
        String numbers = RandomStringUtils.randomNumeric(2); // 2 цифры
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false); // 2 специальных символа

        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        return pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}