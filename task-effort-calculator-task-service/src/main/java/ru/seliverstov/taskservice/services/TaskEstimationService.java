package ru.seliverstov.taskservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.taskservice.exception.ErrorCode;
import ru.seliverstov.taskservice.exception.ServiceException;
import ru.seliverstov.taskservice.mapper.TaskEstimationMapper;
import ru.seliverstov.taskservice.model.dto.AddTaskEstimationRq;
import ru.seliverstov.taskservice.model.dto.TaskEstimationRs;
import ru.seliverstov.taskservice.model.dto.UpdateTaskEstimationRq;
import ru.seliverstov.taskservice.model.entity.Task;
import ru.seliverstov.taskservice.model.entity.TaskUserEstimation;
import ru.seliverstov.taskservice.model.entity.TaskUserEstimationId;
import ru.seliverstov.taskservice.repository.TaskEstimationRepository;
import ru.seliverstov.taskservice.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskEstimationService {
    private final TaskEstimationRepository taskEstimationRepository;
    private final TaskEstimationMapper taskEstimationMapper;
    private final TaskRepository taskRepository;

    public List<TaskEstimationRs> getAll() {
        return taskEstimationRepository.findAll()
            .stream()
            .map(taskEstimationMapper::toTaskEstimationRs)
            .toList();
    }

    public TaskEstimationRs getTaskEstimationById(final Long taskId, final Long userId) {
        final TaskUserEstimationId taskUserEstimationId = TaskUserEstimationId.builder()
            .taskId(taskId)
            .userId(userId)
            .build();
        return taskEstimationRepository.findById(taskUserEstimationId)
            .map(taskEstimationMapper::toTaskEstimationRs)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_003, taskId, userId));
    }

    public TaskEstimationRs postTaskEstimation(final AddTaskEstimationRq request) {
        final Task task = taskRepository.findById(request.getTaskId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, request.getTaskId()));

        final TaskUserEstimation taskUserEstimation = TaskUserEstimation.builder()
            .id(TaskUserEstimationId.builder()
                .taskId(request.getTaskId())
                .userId(request.getUserId())
                .build())
            .task(task)
            .daysPerPerson(request.getDaysPerTask())
            .build();

        taskEstimationRepository.save(taskUserEstimation);
        return taskEstimationMapper.toTaskEstimationRs(taskUserEstimation);
    }

    public void deleteTaskEstimation(final Long taskId, final Long userId) {
        final TaskUserEstimationId taskUserEstimationId = TaskUserEstimationId.builder()
            .taskId(taskId)
            .userId(userId)
            .build();
        taskEstimationRepository.deleteById(taskUserEstimationId);
    }

    @Transactional
    public TaskEstimationRs putTaskEstimation(final UpdateTaskEstimationRq request) {
        final TaskUserEstimationId taskUserEstimationId = TaskUserEstimationId.builder()
            .taskId(request.getTaskId())
            .userId(request.getUserId())
            .build();
        final TaskUserEstimation taskUserEstimation = taskEstimationRepository.findById(taskUserEstimationId)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_003, request.getTaskId(), request.getUserId()));
        taskUserEstimation.setDaysPerPerson(request.getDaysPerTask());
        return taskEstimationMapper.toTaskEstimationRs(taskUserEstimation);
    }
}
