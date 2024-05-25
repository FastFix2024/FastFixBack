package fast_fix.security.sec_filter;

import fast_fix.security.AuthInfo;
import fast_fix.security.sec_service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {

    private TokenService service;

    public TokenFilter(TokenService tokenService) {
        this.service = tokenService;
    }

    private String getTokenFromRequest(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies){
                if ("Access-Token".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        String token = request.getHeader("Authorization");

        //  честно - не помню, что за ХРЕНЬ этот "BEARER"

        if (token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest)request);

        if (token != null && service.validateAccessToken(token)){
            Claims claims = service.getAccessClaims(token);
            AuthInfo authInfo = service.generateAuthInfo(claims);
            authInfo.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authInfo);
        }

        filterChain.doFilter(request, response);
    }
}

