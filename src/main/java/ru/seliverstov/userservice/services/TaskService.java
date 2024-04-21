package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.userservice.dto.TaskRegistrationRq;
import ru.seliverstov.userservice.dto.TaskRq;
import ru.seliverstov.userservice.dto.TaskRs;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.mapper.TaskMapper;
import ru.seliverstov.userservice.model.Task;
import ru.seliverstov.userservice.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskRs> findAll() {
        return taskRepository.findAll()
            .stream()
            .map(taskMapper::toTaskRs)
            .toList();
    }

    public TaskRs getTaskById(final Long id) {
        return taskRepository.findById(id)
            .map(taskMapper::toTaskRs)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, id));
    }

    public TaskRs postTask(final TaskRegistrationRq taskRegistrationRq) {
        final Task task = Task.builder()
            .name(taskRegistrationRq.getTaskName())
            .build();
        taskRepository.save(task);
        return taskMapper.toTaskRs(task);
    }

    public void deleteTask(final Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskRs putTask(final TaskRq taskRq) {
        final Task taskToChange = taskRepository.findById(taskRq.getId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, taskRq.getId()));
        taskToChange.setName(taskRq.getName());
        return taskMapper.toTaskRs(taskToChange);
    }
}
