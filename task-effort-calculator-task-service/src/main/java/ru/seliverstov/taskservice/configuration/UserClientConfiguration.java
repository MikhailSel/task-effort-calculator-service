package ru.seliverstov.taskservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.seliverstov.taskservice.client.UserClient;

@Configuration
public class UserClientConfiguration {

    @Bean
    public UserClient userClient() {
        final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8090")
            .build();

        return new UserClient(webClient);
    }
}
