package ru.seliverstov.userservice.controller;

import org.junit.jupiter.api.Test;
import ru.seliverstov.userservice.model.dto.AddTaskEstimationRq;
import ru.seliverstov.userservice.model.dto.TaskEstimationRs;
import ru.seliverstov.userservice.model.dto.UpdateTaskEstimationRq;
import ru.seliverstov.userservice.model.entity.Task;
import ru.seliverstov.userservice.model.entity.TaskUserEstimation;
import ru.seliverstov.userservice.model.entity.TaskUserEstimationId;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.support.DataProvider;
import ru.seliverstov.userservice.support.IntegrationTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaskEstimationControllerTest extends IntegrationTestBase {

    private static TaskUserEstimationId getTaskUserEstimationId() {
        return TaskUserEstimationId.builder()
            .taskId(1L)
            .userId(1L)
            .build();
    }

    @Test
    void getTestEstimationList() {
        //GIVEN
        final Task task = taskRepository.save(DataProvider.prepareTask().build());
        final User user = userRepository.save(DataProvider.prepareUser().build());

        final TaskUserEstimationId taskUserEstimationId = getTaskUserEstimationId();
        final TaskUserEstimation taskUserEstimation = TaskUserEstimation.builder()
            .id(taskUserEstimationId)
            .task(task)
            .user(user)
            .daysPerPerson(8L)
            .build();
        taskEstimationRepository.save(taskUserEstimation);

        //WHEN
        final List<TaskEstimationRs> taskEstimationRsListActual = getTaskEstimationRsList();
        //THEN
        assertThat(taskEstimationRsListActual)
            .usingRecursiveComparison()
            .isEqualTo(List.of(TaskEstimationRs.builder()
                .taskId(1L)
                .userId(1L)
                .daysPerTask(8L)
                .build()));
    }

    @Test
    void getTestEstimationById() {
        //GIVEN
        final Task task = taskRepository.save(DataProvider.prepareTask().build());
        final User user = userRepository.save(DataProvider.prepareUser().build());
        final TaskUserEstimationId taskUserEstimationId = getTaskUserEstimationId();
        final TaskUserEstimation taskUserEstimation = TaskUserEstimation.builder()
            .id(taskUserEstimationId)
            .task(task)
            .user(user)
            .daysPerPerson(8L)
            .build();
        taskEstimationRepository.save(taskUserEstimation);

        //WHEN

        //THEN
        assertThat(getTaskUserEstimationById())
            .usingRecursiveComparison()
            .isEqualTo(TaskEstimationRs.builder()
                .taskId(1L)
                .userId(1L)
                .daysPerTask(8L)
                .build());
    }

    @Test
    void postTaskEstimationShouldSuccess() {
        //GIVEN
        final Task task = taskRepository.save(DataProvider.prepareTask().build());
        final User user = userRepository.save(DataProvider.prepareUser().build());

        final AddTaskEstimationRq request = AddTaskEstimationRq.builder()
            .taskId(task.getId())
            .userId(user.getId())
            .daysPerTask(8L)
            .build();

        //WHEN
        assertThat(postTaskEstimation(request))
            .usingRecursiveComparison()
            .isEqualTo(TaskEstimationRs.builder()
                .taskId(1L)
                .userId(1L)
                .daysPerTask(8L)
                .build());

        final TaskUserEstimationId taskUserEstimationId = getTaskUserEstimationId();

        transactionTemplate.execute(ts ->
            assertThat(taskEstimationRepository.findById(taskUserEstimationId))
                .get()
                .usingRecursiveComparison()
                .ignoringFields("id", "user", "task")
                .isEqualTo(TaskUserEstimation.builder()
                    .task(task)
                    .user(user)
                    .daysPerPerson(8L)
                    .build())
        );

    }

    @Test
    void deleteTaskEstimationById() {
        //GIVEN
        final Task task = taskRepository.save(DataProvider.prepareTask().build());
        final User user = userRepository.save(DataProvider.prepareUser().build());

        final TaskUserEstimationId taskUserEstimationId = TaskUserEstimationId.builder()
            .userId(1L)
            .taskId(1L)
            .build();
        final TaskUserEstimation taskUserEstimation = TaskUserEstimation.builder()
            .id(taskUserEstimationId)
            .task(task)
            .user(user)
            .daysPerPerson(8L)
            .build();
        taskEstimationRepository.save(taskUserEstimation);
        //WHEN

        //THEN
        deleteTaskEstimation();

        assertThat(taskEstimationRepository.findAll())
            .isEmpty();
    }

    @Test
    void putTaskUserEstimation() {
        //GIVEN
        final Task task = taskRepository.save(DataProvider.prepareTask().build());
        final User user = userRepository.save(DataProvider.prepareUser().build());

        final TaskUserEstimationId taskUserEstimationId = TaskUserEstimationId.builder()
            .userId(1L)
            .taskId(1L)
            .build();
        final TaskUserEstimation taskUserEstimation = TaskUserEstimation.builder()
            .id(taskUserEstimationId)
            .task(task)
            .user(user)
            .daysPerPerson(8L)
            .build();
        taskEstimationRepository.save(taskUserEstimation);
        //WHEN
        final UpdateTaskEstimationRq request = UpdateTaskEstimationRq.builder()
            .taskId(1L)
            .userId(1L)
            .daysPerTask(10L)
            .build();

        //THEN
        assertThat(putTaskEstimation(request))
            .usingRecursiveComparison()
            .isEqualTo(TaskEstimationRs.builder()
                .taskId(1L)
                .userId(1L)
                .daysPerTask(10L)
                .build());
    }

    private List<TaskEstimationRs> getTaskEstimationRsList() {
        return webTestClient.get()
            .uri("api/v1/task-estimations/all")
            .exchange().expectStatus()
            .isOk()
            .expectBodyList(TaskEstimationRs.class)
            .returnResult()
            .getResponseBody();
    }

    private TaskEstimationRs getTaskUserEstimationById() {
        return webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("api/v1/task-estimations")
                .queryParam("taskId", "1")
                .queryParam("userId", "1")
                .build())
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(TaskEstimationRs.class)
            .returnResult()
            .getResponseBody();
    }

    private TaskEstimationRs putTaskEstimation(UpdateTaskEstimationRq request) {
        return webTestClient.put()
            .uri(uriBuilder -> uriBuilder
                .path("api/v1/task-estimations")
                .build())
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(TaskEstimationRs.class)
            .returnResult()
            .getResponseBody();
    }

    private TaskEstimationRs postTaskEstimation(final AddTaskEstimationRq request) {
        return webTestClient
            .post()
            .uri(uriBuilder -> uriBuilder
                .path("api/v1/task-estimations")
                .build())
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(TaskEstimationRs.class)
            .returnResult()
            .getResponseBody();
    }

    private void deleteTaskEstimation() {
        //http://localhost:8085/api/v1/task-estimations?taskId=1&userId=1'
        webTestClient
            .delete()
            .uri(uriBuilder -> uriBuilder
                .path("api/v1/task-estimations")
                .queryParam("taskId", 1L)
                .queryParam("userId", 1L)
                .build())
            .exchange()
            .expectStatus()
            .isOk();
    }
}
