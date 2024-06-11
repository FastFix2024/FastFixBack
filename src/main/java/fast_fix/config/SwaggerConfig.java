package fast_fix.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(
        info = @Info(
                title = "FastFix App",
                description = "Demo application for JSON web tokens",
                version = "1.0.0",
                contact = @Contact(
                        name = "Fast Fix Project 2024",
                        email = "fastfix2024project@gmail.com",
                        url = "https://example.com"
                )
        )
)
public class SwaggerConfig {
}