package ru.seliverstov.userservice.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.seliverstov.userservice.dto.AddCourseStudentsRq;
import ru.seliverstov.userservice.dto.CourseRs;
import ru.seliverstov.userservice.mapper.StudentMapper;
import ru.seliverstov.userservice.model.Course;
import ru.seliverstov.userservice.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    public List<CourseRs> getAllCourses() {

        return  courseRepository.findAll()
            .stream()
            .map(studentMapper::toCourseRs)
            .toList();
    }
    public CourseRs postCourse(AddCourseStudentsRq request)
    {
        Course course = Course.builder()
            .id(request.getCourseId())
            .build();
        courseRepository.save(course);
        return studentMapper.toCourseRs(course);
    }
}
