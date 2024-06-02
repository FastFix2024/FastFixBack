package fast_fix.security.sec_filter;

import fast_fix.security.AuthInfo;
import fast_fix.security.sec_service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) request);
        if (token != null && tokenService.validateAccessToken(token)){
            Claims claims = tokenService.getAccessClaims(token);
            AuthInfo authInfo = tokenService.generateAuthInfo(claims);
            authInfo.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authInfo);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}