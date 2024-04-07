package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.CourseRating;
import ru.seliverstov.userservice.model.CourseRatingKey;

public interface CourseRatingRepository extends JpaRepository<CourseRating, CourseRatingKey> {
}
