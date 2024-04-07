package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seliverstov.userservice.dto.AddCourseRatingRq;
import ru.seliverstov.userservice.dto.AddCourseRatingRs;
import ru.seliverstov.userservice.dto.AddCourseStudentsRq;
import ru.seliverstov.userservice.dto.CourseRs;
import ru.seliverstov.userservice.mapper.CourseRatingMapper;
import ru.seliverstov.userservice.model.Course;
import ru.seliverstov.userservice.model.CourseRating;
import ru.seliverstov.userservice.model.CourseRatingKey;
import ru.seliverstov.userservice.repository.CourseRatingRepository;
import ru.seliverstov.userservice.repository.CourseRepository;
import ru.seliverstov.userservice.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseRatingService{
    private final CourseRatingRepository courseRatingRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseRatingMapper courseRatingMapper;

    public List<AddCourseRatingRs> getAll() {
        return courseRatingRepository.findAll()
            .stream()
            .map(courseRatingMapper::toCourseRatingRs)
            .toList();
    }

    public AddCourseRatingRs getById(final Long studentId, final Long courseId){
        CourseRatingKey key = CourseRatingKey.builder()
            .studentId(studentId)
            .courseId(courseId)
            .build();
        CourseRating courseRating = courseRatingRepository.findById(key)
            .orElseThrow(RuntimeException::new);
        return AddCourseRatingRs.builder()
            .studentId(courseRating.getStudent().getId())
            .courseId(courseRating.getCourse().getId())
            .rating(courseRating.getRating())
            .build();
    }

    public AddCourseRatingRs postCourseRating(AddCourseRatingRq request)
    {
        CourseRating courseRating = CourseRating.builder()
            .student(studentRepository.findById(request.getStudentId())
                .orElseThrow(RuntimeException::new))
            .course(courseRepository.findById(request.getCourseId())
                .orElseThrow(RuntimeException::new))
            .rating(request.getRating())
            .build();

        courseRatingRepository.save(courseRating);
        return AddCourseRatingRs.builder()
            .studentId(request.getStudentId())
            .courseId(request.getCourseId())
            .rating(request.getRating())
            .build();
    }
}
