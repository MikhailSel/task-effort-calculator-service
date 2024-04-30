package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.model.dto.TaskRegistrationRq;
import ru.seliverstov.userservice.model.dto.TaskRq;
import ru.seliverstov.userservice.model.dto.TaskRs;
import ru.seliverstov.userservice.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskRs> getTaskList() {
        return taskService.findAll();
    }

    @GetMapping("{id}")
    public TaskRs getTaskById(@PathVariable final Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskRs postTask(@RequestBody final TaskRegistrationRq request) {
        return taskService.postTask(request);
    }

    @DeleteMapping("{id}")
    public void deleteTaskById(@PathVariable final Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping
    public TaskRs putTask(@RequestBody final TaskRq request) {
        return taskService.putTask(request);
    }
}
