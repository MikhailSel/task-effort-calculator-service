package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.userservice.dto.AddTaskEstimationRq;
import ru.seliverstov.userservice.dto.TaskEstimationRs;
import ru.seliverstov.userservice.dto.UpdateTaskEstimationRq;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.mapper.TaskEstimationMapper;
import ru.seliverstov.userservice.model.Task;
import ru.seliverstov.userservice.model.TaskUserEstimation;
import ru.seliverstov.userservice.model.TaskUserKey;
import ru.seliverstov.userservice.model.User;
import ru.seliverstov.userservice.repository.TaskEstimationRepository;
import ru.seliverstov.userservice.repository.TaskRepository;
import ru.seliverstov.userservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskEstimationService {
    private final TaskEstimationRepository taskEstimationRepository;
    private final TaskEstimationMapper taskEstimationMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskEstimationRs> getAll() {
        return taskEstimationRepository.findAll()
            .stream()
            .map(taskEstimationMapper::toTaskEstimationRs)
            .toList();
    }

    public TaskEstimationRs getTaskEstimationById(final Long taskId, final Long userId) {
        TaskUserKey taskUserKey = TaskUserKey.builder()
            .taskId(taskId)
            .userId(userId)
            .build();
        return taskEstimationRepository.findById(taskUserKey)
            .map(taskEstimationMapper::toTaskEstimationRs)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_003, taskId, userId));
    }

    public TaskEstimationRs postTaskEstimation(final AddTaskEstimationRq request) {
        Task task = taskRepository.findById(request.getTaskId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, request.getTaskId()));
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_002, request.getUserId()));
        TaskUserEstimation taskUserEstimation = TaskUserEstimation.builder()
            .task(task)
            .user(user)
            .daysPerPerson(request.getDaysPerTask())
            .build();
        taskEstimationRepository.save(taskUserEstimation);
        return taskEstimationMapper.toTaskEstimationRs(taskUserEstimation);
    }

    public void deleteTaskEstimation(final Long taskId, final Long userId) {
        TaskUserKey taskUserKey = TaskUserKey.builder()
            .taskId(taskId)
            .userId(userId)
            .build();
        taskEstimationRepository.deleteById(taskUserKey);
    }

    @Transactional
    public TaskEstimationRs putTaskEstimation(final UpdateTaskEstimationRq request){
        TaskUserKey taskUserKey = TaskUserKey.builder()
            .taskId(request.getTaskId())
            .userId(request.getUserId())
            .build();
        TaskUserEstimation taskUserEstimation = taskEstimationRepository.findById(taskUserKey)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_003, request.getTaskId(), request.getUserId()));
        taskUserEstimation.setDaysPerPerson(request.getDaysPerTask());
        return taskEstimationMapper.toTaskEstimationRs(taskUserEstimation);
    }
}
