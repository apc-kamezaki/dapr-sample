package ap.apcom.samples.dapr.aggregator;

import lombok.Data;

@Data
public class FeedTime {
    private long time;
    private String version;
    private String hostname;
}
