package fast_fix.security.sec_filter;

import fast_fix.domain.entity.User;
import fast_fix.security.AuthInfo;
import fast_fix.security.sec_service.TokenService;
import fast_fix.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {

    private final TokenService tokenService;
    private final UserService userService;

    public TokenFilter(TokenService tokenService, @Lazy UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) request);
        if (token != null) {
            if (tokenService.validateAccessToken(token)) {
                Claims claims = tokenService.getAccessClaims(token);
                AuthInfo authInfo = tokenService.generateAuthInfo(claims);
                authInfo.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authInfo);
            } else {
                String refreshToken = getTokenFromCookies((HttpServletRequest) request, "Refresh-Token");
                if (refreshToken != null && tokenService.validateRefreshToken(refreshToken)) {
                    Claims refreshClaims = tokenService.getRefreshClaims(refreshToken);
                    String username = refreshClaims.getSubject();
                    User user = (User) userService.loadUserByUsername(username);
                    String newAccessToken = tokenService.generateAccessToken(user);

                    Cookie accessTokenCookie = new Cookie("Access-Token", newAccessToken);
                    accessTokenCookie.setPath("/");
                    accessTokenCookie.setHttpOnly(true);
                    ((HttpServletResponse) response).addCookie(accessTokenCookie);

                    Claims claims = tokenService.getAccessClaims(newAccessToken);
                    AuthInfo authInfo = tokenService.generateAuthInfo(claims);
                    authInfo.setAuthenticated(true);
                    SecurityContextHolder.getContext().setAuthentication(authInfo);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Access-Token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
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