package ru.seliverstov.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.userservice.dto.CourseRs;
import ru.seliverstov.userservice.dto.StudentRs;
import ru.seliverstov.userservice.model.Course;
import ru.seliverstov.userservice.model.Student;

import java.util.List;

@Component
public class StudentMapper {
    public StudentRs toStrudentRs(Student student){
        return StudentRs.builder()
            .id(student.getId())
            //.CursesRs(toListCourseRS(student.getLikedCourses()))
            .build();
    }
    public CourseRs toCourseRs(Course course){
        return CourseRs.builder()
            .id(course.getId())
            .build();
    }
    public List<CourseRs> toListCourseRS(List<Course> courses){
        return courses.stream()
            .map(this::toCourseRs)
            .toList();
    }
}
