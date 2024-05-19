package ru.seliverstov.userservice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRq;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRs;
import ru.seliverstov.userservice.model.dto.PutVacationPeriodRq;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.model.entity.VacationPeriod;
import ru.seliverstov.userservice.support.DataProvider;
import ru.seliverstov.userservice.support.IntegrationTestBase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VacationPeriodControllerTest extends IntegrationTestBase {
    @Test
    void testGetVacationPeriod() {
        //GIVEN
        final User user = DataProvider.prepareUser().role(roleRepository.findByRoleName("BackDev"))
            .build();
        userRepository.save(user);
        vacationPeriodRepository.save(DataProvider.prepareVacationPeriod()
            .user(user).build());
        //WHEN
        final WebTestClient.ResponseSpec response = webTestClient.get()
            .uri(uriBuilder -> uriBuilder.pathSegment("api", "v1", "vacation-periods", "1")
                .build())
            .exchange();
        //THEN
        final AddVacationPeriodRs addVacationPeriodRs = response.expectStatus()
            .isOk()
            .expectBody(AddVacationPeriodRs.class)
            .returnResult()
            .getResponseBody();

        assertThat(addVacationPeriodRs).usingRecursiveComparison()
            .isEqualTo(AddVacationPeriodRs.builder()
                .id(1L)
                .dateFrom("01.05.2024")
                .dateTo("31.05.2024")
                .userId(1L)
                .build());
    }

    @Test
    void testPostVacationPeriod() {
        //GIVEN
        final User user = DataProvider.prepareUser().role(roleRepository.findByRoleName("BackDev"))
            .build();
        userRepository.save(user);
        final AddVacationPeriodRq request = AddVacationPeriodRq.builder()
            .userId(1L)
            .dateFrom("01.05.2024")
            .dateTo("31.05.2024")
            .build();
        //WHEN
        final WebTestClient.ResponseSpec response = webTestClient.post()
            .uri("api/v1/vacation-periods")
            .bodyValue(request)
            .exchange();
        //THEN
        final AddVacationPeriodRs addVacationPeriodRs = response.expectStatus()
            .isOk()
            .expectBody(AddVacationPeriodRs.class)
            .returnResult()
            .getResponseBody();
        assertThat(addVacationPeriodRs).usingRecursiveComparison()
            .isEqualTo(AddVacationPeriodRs.builder()
                .id(1L)
                .dateFrom("01.05.2024")
                .dateTo("31.05.2024")
                .userId(1L)
                .build());
        transactionTemplate.execute(ts -> assertThat(vacationPeriodRepository.findById(1L)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_004, 1L)))
            .isEqualTo(VacationPeriod.builder()
                .id(1L)
                .dateFrom(vacationPeriodMapper.toLocalDate("01.05.2024"))
                .dateTo(vacationPeriodMapper.toLocalDate("31.05.2024"))
                .build()));
    }

    @Test
    void testDeleteVacationPeriod() {
        //GIVEN
        final User user = DataProvider.prepareUser().role(roleRepository.findByRoleName("BackDev"))
            .build();
        userRepository.save(user);
        vacationPeriodRepository.save(DataProvider.prepareVacationPeriod()
            .user(user).build());
        //WHEN
        webTestClient.delete()
            .uri(uriBuilder -> uriBuilder.pathSegment("api", "v1", "vacation-periods", "1")
                .build())
            .exchange();
        //THEN
        Assertions.assertThat(vacationPeriodRepository.findAll())
            .isEmpty();
    }

    @Test
    void testPutVacationPeriod() {
        //GIVEN
        final User user = DataProvider.prepareUser().role(roleRepository.findByRoleName("BackDev"))
            .build();
        userRepository.save(user);
        vacationPeriodRepository.save(DataProvider.prepareVacationPeriod()
            .user(user).build());
        //WHEN
        final PutVacationPeriodRq request = PutVacationPeriodRq.builder()
            .id(1L)
            .userId(1L)
            .dateFrom("01.06.2024")
            .dateTo("30.06.2024")
            .build();
        final WebTestClient.ResponseSpec response = webTestClient.put()
            .uri("api/v1/vacation-periods")
            .bodyValue(request)
            .exchange();
        //THEN
        final AddVacationPeriodRs addVacationPeriodRs = response.expectStatus()
            .isOk()
            .expectBody(AddVacationPeriodRs.class)
            .returnResult()
            .getResponseBody();
        assertThat(addVacationPeriodRs)
            .usingRecursiveComparison()
            .isEqualTo(AddVacationPeriodRs.builder()
                .id(1L)
                .userId(1L)
                .dateFrom("01.06.2024")
                .dateTo("30.06.2024")
                .build());
        transactionTemplate.execute(ts -> assertThat(vacationPeriodRepository.findById(1L)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_004, 1L)))
            .isEqualTo(VacationPeriod.builder()
                .id(1L)
                .dateFrom(vacationPeriodMapper.toLocalDate("01.06.2024"))
                .dateTo(vacationPeriodMapper.toLocalDate("30.06.2024"))
                .build()));
    }
}
