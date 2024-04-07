package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
