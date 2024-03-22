package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.Task;
import ru.seliverstov.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

