package ap.apcom.samples.dapr.aggregator.publishers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import ap.apcom.samples.dapr.aggregator.configurations.BackendConfig;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderPublisherService {
    private final BackendConfig backendConfig;
    private final @Qualifier("orderTopicWebClient") WebClient orderTopicWebClient;

    public Mono<Void> publishSimpleOrder(String name) {
        var path = backendConfig.getOrderTopicBase();
        var request = SimpleOrderRequest.builder().orderName(name).build();
        return orderTopicWebClient.post().uri(path)
        .accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(request))
        .retrieve()
        .bodyToMono(Void.class);
    }
}
