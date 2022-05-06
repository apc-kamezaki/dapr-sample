package ap.apcom.samples.dapr.aggregator.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "timefeedWebClient")
    public WebClient timefeedWebClient(BackendConfig config) {
        return WebClient.create(config.getTimefeedHost());
    }

    @Bean(name = "orderTopicWebClient")
    public WebClient orderTopicWebClient(BackendConfig config) {
        return WebClient.builder()
            .baseUrl(config.getOrderTopicHost())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
