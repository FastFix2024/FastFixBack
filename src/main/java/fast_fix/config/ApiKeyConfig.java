package fast_fix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {

    @Value("${google.api.key}")
    private String googleApiKey;

    @Value("${tankerkoenig.api.key}")
    private String tankerkoenigApiKey;

    public String getGoogleApiKey() {
        return googleApiKey;
    }

    public String getTankerkoenigApiKey() {
        return tankerkoenigApiKey;
    }
}