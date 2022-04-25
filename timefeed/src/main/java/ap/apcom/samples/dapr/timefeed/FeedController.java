package ap.apcom.samples.dapr.timefeed;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timefeed")
public class FeedController {
    private final ServerConfig serverConfig;
    private final BuildProperties properties;
    
    @RequestMapping()
    public Mono<FeedTime> get() {
        return Mono.just(
            FeedTime.builder()
            .hostname(serverConfig.getHostname())
            .version(properties.getVersion())
            .time(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond())
            .build());
    }
}
