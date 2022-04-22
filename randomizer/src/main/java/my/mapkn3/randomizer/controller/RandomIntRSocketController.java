package my.mapkn3.randomizer.controller;

import lombok.RequiredArgsConstructor;
import my.mapkn3.randomizer.service.RandomIntService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class RandomIntRSocketController {
    private final RandomIntService randomIntService;

    @MessageMapping("ints")
    public Flux<Integer> getRandomInt(int bound) {
        return randomIntService.generateRandomInts(bound);
    }
}