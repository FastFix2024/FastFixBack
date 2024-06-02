package fast_fix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {

    @Value("${tankerkoenig.api.key}")
    private String tankerkoenigApiKey;

    public String getTankerkoenigApiKey() {
        return tankerkoenigApiKey;
    }
}