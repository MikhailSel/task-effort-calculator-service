package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.entity.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
