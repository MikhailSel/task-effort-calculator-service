package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.model.dto.AddTaskEstimationRq;
import ru.seliverstov.userservice.model.dto.TaskEstimationRs;
import ru.seliverstov.userservice.model.dto.UpdateTaskEstimationRq;
import ru.seliverstov.userservice.services.TaskEstimationService;

import java.util.List;

@RestController
@RequestMapping("api/v1/task-estimations")
@RequiredArgsConstructor
public class TaskEstimationController {
    private final TaskEstimationService taskEstimationService;

    @GetMapping("all")
    public List<TaskEstimationRs> getAll() {
        return taskEstimationService.getAll();
    }

    //http://loclahost:8085/api/v1/task-estimations?userId=1&taskId=1
    @GetMapping
    public TaskEstimationRs getTaskEstimationById(@RequestParam final Long taskId,
                                                  @RequestParam final Long userId) {
        return taskEstimationService.getTaskEstimationById(taskId, userId);
    }

    @PostMapping
    public TaskEstimationRs postTaskEstimation(@RequestBody final AddTaskEstimationRq request) {
        return taskEstimationService.postTaskEstimation(request);
    }

    @PutMapping
    public TaskEstimationRs putTaskEstimation(@RequestBody final UpdateTaskEstimationRq request) {
        return taskEstimationService.putTaskEstimation(request);
    }

    @DeleteMapping
    public void deleteTaskEstimation(@RequestParam final Long taskId,
                                     @RequestParam final Long userId) {
        taskEstimationService.deleteTaskEstimation(taskId, userId);
    }
}
