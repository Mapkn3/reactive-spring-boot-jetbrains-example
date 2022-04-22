package my.mapkn3.randomizer.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomIntService {
    public Flux<Integer> generateRandomInts(int bound) {
        return Flux.interval(Duration.ofMillis(500))
                .map(ignore -> randomInt(bound));
    }

    private int randomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}