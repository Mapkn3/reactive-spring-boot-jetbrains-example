package my.mapkn3.randomizerclient.client;

import reactor.core.publisher.Flux;

public interface RandomizerClient {
    Flux<Integer> ints(int bound);
}