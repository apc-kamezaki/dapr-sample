package ap.apcom.samples.dapr.aggregator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceInvokeController {
    private final TimeFeedService timeFeedService;

    @GetMapping()
    public Mono<String> rest(Model model) {
        return Mono.zip(timeFeedService.getAggregatorFeed(), timeFeedService.getServerFeed())
        .map(tuple -> {
            model.addAttribute("aggregator", tuple.getT1());
            model.addAttribute("feed", tuple.getT2());
            return "rest";
        });
    }

}
