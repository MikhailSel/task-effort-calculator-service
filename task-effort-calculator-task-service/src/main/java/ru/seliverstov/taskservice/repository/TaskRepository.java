package ru.seliverstov.taskservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.taskservice.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
