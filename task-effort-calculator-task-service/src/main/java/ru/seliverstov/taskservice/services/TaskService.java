package ru.seliverstov.taskservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.taskservice.client.UserClient;
import ru.seliverstov.taskservice.exception.ErrorCode;
import ru.seliverstov.taskservice.exception.ServiceException;
import ru.seliverstov.taskservice.mapper.TaskMapper;
import ru.seliverstov.taskservice.model.dto.CreateTaskRq;
import ru.seliverstov.taskservice.model.dto.TaskRq;
import ru.seliverstov.taskservice.model.dto.TaskRs;
import ru.seliverstov.taskservice.model.entity.Task;
import ru.seliverstov.taskservice.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserClient userClient;

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

    public TaskRs postTask(final CreateTaskRq createTaskRq) {
        userClient.getUser(createTaskRq.getAssigneeEmail());

        final Task task = Task.builder()
            .reporterId(createTaskRq.getReporterId())
//            .assigneeId()
            .name(createTaskRq.getTaskName())
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
