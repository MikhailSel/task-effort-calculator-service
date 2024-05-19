package ru.seliverstov.taskservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.taskservice.model.dto.TaskEstimationRs;
import ru.seliverstov.taskservice.model.entity.TaskUserEstimation;

@Component
public class TaskEstimationMapper {

    public TaskEstimationRs toTaskEstimationRs(final TaskUserEstimation taskUserEstimation) {
        return TaskEstimationRs.builder()
            .taskId(taskUserEstimation.getTask().getId())
            .daysPerTask(taskUserEstimation.getDaysPerPerson())
            .build();
    }
}
