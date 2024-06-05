package fast_fix.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

    @OpenAPIDefinition(
            info = @Info(
                    title = "FastFix demo app",
                    description = "Demo application for JSON web tokens",
                    version = "1.0.0",
                    contact = @Contact(
                            name = "Fast Fix project 2024",
                            email = "fastfix2024project@gmail.com",
                            url = "https://www.jury.looga.de/"
                    )
            )
    )
    public class SwaggerConfig {
}
