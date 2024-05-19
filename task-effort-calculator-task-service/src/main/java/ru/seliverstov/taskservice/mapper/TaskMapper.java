package ru.seliverstov.taskservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.taskservice.model.dto.TaskRs;
import ru.seliverstov.taskservice.model.entity.Task;

@Component
public class TaskMapper {
    public TaskRs toTaskRs(final Task task) {
        return TaskRs.builder()
            .id(task.getId())
            .name(task.getName())
            .build();
    }
}
