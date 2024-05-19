package ru.seliverstov.taskservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.reactive.function.client.WebClient;
import ru.seliverstov.taskservice.model.dto.UserDtoResponse;

@RequiredArgsConstructor
public class UserClient {
    private final WebClient webClient;

    public UserDtoResponse getUser(final String email) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("api/v1/users")
                .queryParam("email", email)
                .build())
            .retrieve()
            .toEntity(UserDtoResponse.class)
            .mapNotNull(HttpEntity::getBody)
            .block();
    }
}
