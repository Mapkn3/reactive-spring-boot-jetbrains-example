package my.mapkn3.randomizerclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("webclient")
class WebClientRandomizerClientIntegrationTest {
    @Autowired
    private RandomizerClient webClientRandomizerClient;

    @Test
    void shouldRetrieveRandomIntsFromRandomizerService() {
        var ints = webClientRandomizerClient.ints(10);

        assertNotNull(ints);
        var fiveInts = ints.take(5);
        assertEquals(5, fiveInts.count().block());
    }
}