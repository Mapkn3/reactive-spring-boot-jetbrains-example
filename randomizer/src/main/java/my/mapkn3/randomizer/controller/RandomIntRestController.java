package my.mapkn3.randomizer.controller;

import lombok.RequiredArgsConstructor;
import my.mapkn3.randomizer.service.RandomIntService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class RandomIntRestController {
    private final RandomIntService randomIntService;

    @GetMapping(
            value = "/ints/{bound}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<Integer> getRandomIntStream(@PathVariable int bound) {
        return randomIntService.generateRandomInts(bound);
    }
}