package ru.seliverstov.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.userservice.model.dto.TaskRs;
import ru.seliverstov.userservice.model.entity.Task;

@Component
public class TaskMapper {
    public TaskRs toTaskRs(final Task task) {
        return TaskRs.builder()
            .id(task.getId())
            .name(task.getName())
            .build();
    }
}
