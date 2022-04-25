package ap.apcom.samples.dapr.aggregator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("timefeedWebClient")
    public WebClient timefeedWebClient(BackendConfig config) {
        return WebClient.create(config.getTimefeedHost());
    }
}
