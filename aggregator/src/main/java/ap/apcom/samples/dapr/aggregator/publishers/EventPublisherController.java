package ap.apcom.samples.dapr.aggregator.publishers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/publish")
public class EventPublisherController {
    private final OrderPublisherService publisherService;

    @RequestMapping(path = "simple", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Mono<Void> publishSimpleOrder(@RequestBody AggregatorSimpleRequest request) {
        return publisherService.publishSimpleOrder(request.orderName);
    }
}

class AggregatorSimpleRequest {
    public String orderName;
}
