package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.model.Task;
import ru.seliverstov.userservice.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll()
            .stream()
            .toList();
    }

    public Task getTaskById(final Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, id));
    }

    public Task postTask(final Task task) {
        taskRepository.save(task);
        return task;
    }

    public void deleteTask(final Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            throw new ServiceException(ErrorCode.ERR_CODE_001, id);
        }
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task putTask(final Task task) {
        Task taskToChange = taskRepository.findById(task.getId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_001, task.getId()));
        taskToChange.setName(task.getName());
        return taskToChange;
    }
}
