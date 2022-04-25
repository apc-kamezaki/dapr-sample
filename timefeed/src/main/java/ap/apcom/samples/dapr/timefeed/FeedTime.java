package ap.apcom.samples.dapr.timefeed;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FeedTime {
    private long time;
    private String version;
    private String hostname;
}
