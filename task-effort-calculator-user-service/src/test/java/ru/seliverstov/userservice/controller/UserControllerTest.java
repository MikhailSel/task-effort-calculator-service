package ru.seliverstov.userservice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.model.dto.UserRegistrationRq;
import ru.seliverstov.userservice.model.dto.UserRs;
import ru.seliverstov.userservice.model.dto.UserUpdateRq;
import ru.seliverstov.userservice.model.entity.Role;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.support.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest extends IntegrationTestBase {

    @Test
    void getUserById() {
        //GIVEN
        //userRepository.save(DataProvider.prepareUser().build());
        final Role role = roleRepository.findByRoleName("BackDev");
        final User user = User.builder()
            .fio("user1")
            .role(role)
            .build();
        userRepository.save(user);
        //WHEN
        final WebTestClient.ResponseSpec response =
            webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/v1/users")
                    .queryParam("id", "1")
                    .build())
                .exchange();
        //THEN
        response
            .expectStatus()
            .isOk()
            .expectBody(UserRs.class)
            .isEqualTo(UserRs.builder()
                .id(1L)
                .fio("user1")
                .role("BackDev")
                .build());
    }

    @Test
    void testPostUser() {
        //GIVEN
        final UserRegistrationRq request = UserRegistrationRq.builder()
            .fullName("user1")
            .role("BackDev")
            .build();
        //WHEN
        final WebTestClient.ResponseSpec response =
            webTestClient
                .post()
                .uri("api/v1/users")
                .bodyValue(request)
                .exchange();
        //THEN
        response
            .expectStatus()
            .isOk()
            .expectBody(UserRs.class)
            .isEqualTo(UserRs.builder()
                .id(1L)
                .fio("user1")
                .role("BackDev")
                .build());
        final Role role = roleRepository.findByRoleName("BackDev");
        transactionTemplate.execute(ts -> assertThat(userRepository
            .findById(1L)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, 1L)))
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(User.builder()
                .fio("user1")
                .role(role)
                .build()));
    }

    @Test
    void testDeleteUser() {
        //GIVEN
        final Role role = roleRepository.findByRoleName("BackDev");
        final User user = User.builder()
            .fio("user1")
            .role(role)
            .build();
        userRepository.save(user);
        //WHEN
        final WebTestClient.ResponseSpec response =
            webTestClient.delete()
                .uri(uriBuilder -> uriBuilder.path("api/v1/users")
                    .queryParam("id", "1")
                    .build())
                .exchange();
        //THEN
        response
            .expectStatus()
            .isOk();

        Assertions.assertThat(userRepository.findAll())
            .isEmpty();
    }

    @Test
    void testPutUser() {
        //GIVEN
        final Role role = roleRepository.findByRoleName("BackDev");
        final User user = User.builder()
            .fio("user1")
            .role(role)
            .build();
        userRepository.save(user);
        //WHEN
        final UserUpdateRq request = UserUpdateRq.builder()
            .fio("user2")
            .id(1L)
            .build();
        final WebTestClient.ResponseSpec response =
            webTestClient
                .put()
                .uri("api/v1/users")
                .bodyValue(request)
                .exchange();
        //THEN
        response.expectStatus()
            .isOk()
            .expectBody(UserRs.class)
            .isEqualTo(UserRs.builder()
                .id(1L)
                .fio("user2")
                .role("BackDev")
                .build());
        transactionTemplate.execute(ts -> assertThat(userRepository
            .findById(1L)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_002, 1L)))
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(User.builder()
                .fio("user2")
                .role(role)
                .build()));
    }
}
