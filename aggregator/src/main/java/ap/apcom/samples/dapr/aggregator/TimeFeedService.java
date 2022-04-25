package ap.apcom.samples.dapr.aggregator;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TimeFeedService {
    private final ServerConfig serverConfig;
    private final BackendConfig backendConfig;
    private final BuildProperties properties;
    private final @Qualifier("timefeedWebClient") WebClient timefeedClient;

    public Mono<FeedTime> getAggregatorFeed() {
        var feed = new FeedTime();
        feed.setHostname(serverConfig.getHostname());
        feed.setVersion(properties.getVersion());
        feed.setTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
        return Mono.just(feed);
    }

    public Mono<FeedTime> getServerFeed() {
        var path = Path.of(backendConfig.getTimefeedBase(), "timefeed").toString();
        return timefeedClient.get().uri(path)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(FeedTime.class);
    }
}
