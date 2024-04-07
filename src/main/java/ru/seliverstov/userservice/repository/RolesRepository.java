package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
