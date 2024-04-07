package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
