package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.TaskUserEstimation;
import ru.seliverstov.userservice.model.TaskUserEstimationId;

public interface TaskEstimationRepository extends JpaRepository<TaskUserEstimation, TaskUserEstimationId> {
}
