package fast_fix.configuration;

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

    private TokenFilter filter;

    public SecurityConfig(TokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.POST,"/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/auth/login", "/api/auth/access").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/auth/logout").hasAnyRole("ADMIN","USER")
// TODO                   .requestMatchers(HttpMethod.POST, "/api/bookmarks/add").hasAnyRole("Admin","User")
// TODO                   .requestMatchers(HttpMethod.GET, "/api/bookmarks/user/{userId}").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET, "/api/insurance-companies/all").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET, "/api/insurance-companies").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST, "/api/insurance-companies").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/insurance-companies/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/insurance-companies/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN","USER")
                        .anyRequest().permitAll())
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}