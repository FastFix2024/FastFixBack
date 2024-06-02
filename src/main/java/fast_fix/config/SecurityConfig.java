package fast_fix.config;

import fast_fix.security.sec_filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenFilter tokenFilter;

    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/access").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/{email}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/{userId}/email").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/{userId}/password").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/{userId}/fuel-type").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/{userId}/insurance-company").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/{userId}/maintenance-date").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/users/logout").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/notifications/user/{userId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/notifications/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/notifications/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/api/fuel-stations").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/api/emergency-contact").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/api/car-insurance-companies").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/api/car-insurance-companies/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST,"/api/car-insurance-companies").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/car-insurance-companies/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/car-insurance-companies/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/api/car-details/user/{userId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT,"/api/car-details/user/{userId}").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()) //permitAll() в целях тестирования - после поменять на authenticated()
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}