package ru.seliverstov.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.userservice.dto.AddCourseRatingRq;
import ru.seliverstov.userservice.dto.AddCourseRatingRs;
import ru.seliverstov.userservice.model.CourseRating;

@Component
public class CourseRatingMapper {
    public AddCourseRatingRs toCourseRatingRs(CourseRating courseRating){
        return AddCourseRatingRs.builder()
            .studentId(courseRating.getStudent().getId())
            .courseId(courseRating.getCourse().getId())
            .rating(courseRating.getRating())
            .build();
    }
}
