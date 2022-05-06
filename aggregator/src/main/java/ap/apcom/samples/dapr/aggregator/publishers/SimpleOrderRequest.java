package ap.apcom.samples.dapr.aggregator.publishers;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SimpleOrderRequest {
    private String orderName;    
}