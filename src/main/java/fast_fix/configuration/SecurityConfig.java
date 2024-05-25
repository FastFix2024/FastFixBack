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
import org.springframework.security.crypto.password.PasswordEncoder;
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
                        .requestMatchers(HttpMethod.GET,"/customers").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/products/all").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/auth/login", "/auth/access").permitAll()
                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/files").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/system/products").hasRole("SUPPLIER")
//                        .anyRequest().authenticated())
                        .anyRequest().permitAll())
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}