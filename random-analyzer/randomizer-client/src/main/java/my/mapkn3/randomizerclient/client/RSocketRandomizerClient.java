package my.mapkn3.randomizerclient.client;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Log4j2
public class RSocketRandomizerClient implements RandomizerClient {

    RSocketRequester rSocketRequester;

    @Override
    public Flux<Integer> ints(int bound) {
        log.info("RSocket randomizer client");
        return rSocketRequester.route("ints")
                .data(10)
                .retrieveFlux(Integer.class)
                .retryWhen(Retry.backoff(5, Duration.ofMillis(500)).maxBackoff(Duration.ofSeconds(5)))
                .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}