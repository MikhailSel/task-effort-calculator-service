package ru.seliverstov.taskservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.seliverstov.taskservice.exception.ErrorCode;
import ru.seliverstov.taskservice.exception.ServiceException;
import ru.seliverstov.taskservice.model.dto.CreateTaskRq;
import ru.seliverstov.taskservice.model.dto.TaskRq;
import ru.seliverstov.taskservice.model.dto.TaskRs;
import ru.seliverstov.taskservice.model.entity.Task;
import ru.seliverstov.taskservice.support.DataProvider;
import ru.seliverstov.taskservice.support.IntegrationTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskControllerTest extends IntegrationTestBase {

    @Test
    void testGetTaskList() {
        //GIVEN
        taskRepository.save(DataProvider.prepareTask().build());
        //WHEN
        final WebTestClient.ResponseSpec response = webTestClient.get()
            .uri("api/v1/tasks")
            .exchange();
        //THEN
        response
            .expectStatus()
            .isOk()
            .expectBodyList(TaskRs.class)
            .isEqualTo(List.of(TaskRs.builder()
                .name("task1")
                .id(1L)
                .build()));
    }

    @Test
    void testGetTaskById() {
        //GIVEN
        taskRepository.save(DataProvider.prepareTask().build());
        //WHEN
        final WebTestClient.ResponseSpec response = webTestClient.get()
            .uri(uriBuilder -> uriBuilder
                .pathSegment("api", "v1", "tasks", "1")
                .build())
            .exchange();
        //THEN
        response
            .expectStatus()
            .isOk()
            .expectBody(TaskRs.class)
            .isEqualTo(TaskRs.builder()
                .name("task1")
                .id(1L)
                .build());
    }

    @Test
    void testDeleteTaskById() {
        //GIVEN
        taskRepository.save(DataProvider.prepareTask().build());
        //WHEN
        final WebTestClient.ResponseSpec response = webTestClient
            .delete()
            .uri(uriBuilder -> uriBuilder
                .pathSegment("api", "v1", "tasks", "1")
                .build())
            .exchange();
        //THEN
        response
            .expectStatus()
            .isOk();

        assertThat(taskRepository.findAll())
            .isEmpty();
    }

    @Test
    void testPostTask() {
        //GIVEN
        final CreateTaskRq request = CreateTaskRq.builder()
            .taskName("task1")
            .build();
        //WHEN
        final WebTestClient.ResponseSpec response = webTestClient
            .post()
            .uri("api/v1/tasks")
            .bodyValue(request)
            .exchange();
        //THEN
        response.expectStatus()
            .isOk()
            .expectBody(TaskRs.class)
            .isEqualTo(TaskRs.builder()
                .id(1L)
                .name("task1")
                .build());
        transactionTemplate.execute(ts ->
            assertThat(taskRepository.findById(1L)
                .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, 1L)))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(DataProvider.prepareTask().build()));
    }

    @Test
    void testPutTask() {
        //GIVEN
        taskRepository.save(DataProvider.prepareTask().build());
        //WHEN
        final TaskRq request = TaskRq.builder()
            .id(1L)
            .name("task2")
            .build();
        final WebTestClient.ResponseSpec response = webTestClient.put()
            .uri("api/v1/tasks")
            .bodyValue(request)
            .exchange();
        //WHEN
        response
            .expectStatus()
            .isOk()
            .expectBody(TaskRs.class)
            .isEqualTo(TaskRs.builder()
                .id(1L)
                .name("task2")
                .build());
        transactionTemplate.execute(ts -> assertThat(taskRepository.findById(1L)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_002, 1L)))
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(Task.builder()
                .name("task2")
                .build()));
    }
}
