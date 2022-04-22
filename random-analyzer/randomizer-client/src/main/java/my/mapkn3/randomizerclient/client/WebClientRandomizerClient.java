package my.mapkn3.randomizerclient.client;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;

import static lombok.AccessLevel.PRIVATE;


@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Log4j2
public class WebClientRandomizerClient implements RandomizerClient {
    WebClient webClient;

    @Override
    public Flux<Integer> ints(int bound) {
        log.info("Web client randomizer client");
        return webClient.get()
                .uri("http://localhost:8080/ints/" + bound)
                .retrieve()
                .bodyToFlux(Integer.class)
                .retryWhen(Retry.backoff(5, Duration.ofMillis(500)).maxBackoff(Duration.ofSeconds(5)))
                .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}