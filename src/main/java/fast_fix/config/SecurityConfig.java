package fast_fix.config;

import fast_fix.security.sec_filter.TokenFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
    public OpenAPI openAPI(){
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("FastFix demo app")
                        .description("Demo application for JSON web tokens")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("FastFix")
                                .email("fastfix2024project@gmail.com")
                                .url("https://www.ait-tr.de/"))
                        .license(new License().name("@FastFix").url("https://www.ait-tr.de/")));
    }

    private SecurityScheme createAPIKeyScheme(){
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        //register
                        .requestMatchers(HttpMethod.POST, "/api/register", "/api/auth/login").permitAll()
                        //logout
                        .requestMatchers(HttpMethod.POST, "/api/auth/logout").authenticated()
                        //access
                        .requestMatchers(HttpMethod.POST, "/api/auth/access").permitAll()
                        //getUserProfile
                        .requestMatchers(HttpMethod.GET, "/api/users/my/profile").authenticated()
                        //updateUserProfile
                        .requestMatchers(HttpMethod.PUT, "/api/users/my/profile").authenticated()
                        //deleteUser
                        .requestMatchers(HttpMethod.DELETE, "/api/users/my/profile").authenticated()
                        //getUserProfile (any)
                        .requestMatchers(HttpMethod.GET, "/api/users/{username}").permitAll()
                        //getCarDetails
                        .requestMatchers(HttpMethod.GET,"/api/car-details/{userId}").authenticated()
                        //updateCarDetails of User
                        .requestMatchers(HttpMethod.PUT,"/api/car-details/{userId}/**").authenticated()
                        //getCarDetails of User
                        .requestMatchers(HttpMethod.GET,"/api/car-details/fuel-types", "/api/car-details/stations", "/api/car-details/insurance-companies").authenticated()
                        .anyRequest().permitAll())
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}