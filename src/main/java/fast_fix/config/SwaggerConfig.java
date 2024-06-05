package fast_fix.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

    @OpenAPIDefinition(
            info = @Info(
                    title = "JWT demo app",
                    description = "Demo application for JSON web tokens",
                    version = "1.0.0",
                    contact = @Contact(
                            name = "Juri Looga",
                            email = "looga.jury@gmail.com",
                            url = "https://www.jurylooga.de"
                    )
            )
    )
    public class SwaggerConfig {
}
