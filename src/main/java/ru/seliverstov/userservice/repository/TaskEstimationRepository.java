package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.entity.TaskUserEstimation;
import ru.seliverstov.userservice.model.entity.TaskUserEstimationId;

public interface TaskEstimationRepository extends JpaRepository<TaskUserEstimation, TaskUserEstimationId> {
}
