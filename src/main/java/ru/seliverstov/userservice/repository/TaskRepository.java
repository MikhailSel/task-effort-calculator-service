package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
