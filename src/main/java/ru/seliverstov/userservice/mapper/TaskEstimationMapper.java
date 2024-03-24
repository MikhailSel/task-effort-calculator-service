package ru.seliverstov.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.userservice.dto.TaskEstimationRs;
import ru.seliverstov.userservice.model.TaskUserEstimation;

@Component
public class TaskEstimationMapper {

    public TaskEstimationRs toTaskEstimationRs(final TaskUserEstimation taskUserEstimation) {
        return TaskEstimationRs.builder()
            .taskId(taskUserEstimation.getTask().getId())
            .userId(taskUserEstimation.getUser().getId())
            .daysPerTask(taskUserEstimation.getDaysPerPerson())
            .build();
    }
}
