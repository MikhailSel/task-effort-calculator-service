package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.seliverstov.userservice.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * FROM roles WHERE name=?", nativeQuery = true)
    Role findByRoleName(String roleName);
}
