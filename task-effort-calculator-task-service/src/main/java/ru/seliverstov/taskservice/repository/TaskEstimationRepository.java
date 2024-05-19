package ru.seliverstov.taskservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.taskservice.model.entity.TaskUserEstimation;
import ru.seliverstov.taskservice.model.entity.TaskUserEstimationId;

public interface TaskEstimationRepository extends JpaRepository<TaskUserEstimation, TaskUserEstimationId> {
}
