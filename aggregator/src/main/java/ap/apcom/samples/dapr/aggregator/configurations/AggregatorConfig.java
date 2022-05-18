package ap.apcom.samples.dapr.aggregator.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "aggregator")
public class AggregatorConfig {
    private String titleVersion;
}
