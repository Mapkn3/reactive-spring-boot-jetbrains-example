package my.mapkn3.randomizerclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("rsocket")
class RSocketRandomizerClientIntegrationTest {
    @Autowired
    private RandomizerClient rSocketRandomizerClient;

    @Test
    void shouldRetrieveRandomIntsFromRandomizerService() {
        var ints = rSocketRandomizerClient.ints(10);

        assertNotNull(ints);
        StepVerifier.create(ints.take(5))
                .expectNextMatches(i -> i >= 0 && i < 10)
                .expectNextMatches(i -> i >= 0 && i < 10)
                .expectNextMatches(i -> i >= 0 && i < 10)
                .expectNextMatches(i -> i >= 0 && i < 10)
                .expectNextMatches(i -> i >= 0 && i < 10)
                .verifyComplete();
    }
}