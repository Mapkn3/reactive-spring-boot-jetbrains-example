package my.mapkn3.randomizerclient.config;

import my.mapkn3.randomizerclient.client.RSocketRandomizerClient;
import my.mapkn3.randomizerclient.client.RandomizerClient;
import my.mapkn3.randomizerclient.client.WebClientRandomizerClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Bean
    @Profile("webclient")
    public RandomizerClient webClientRandomizerClient(WebClient webClient) {
        return new WebClientRandomizerClient(webClient);
    }

    @Bean
    @Profile("rsocket")
    public RandomizerClient rSocketRandomizerClient(RSocketRequester rSocketRequester) {
        return new RSocketRandomizerClient(rSocketRequester);
    }

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.tcp("localhost", 7000);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}