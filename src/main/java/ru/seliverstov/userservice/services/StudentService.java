package ru.seliverstov.userservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seliverstov.userservice.dto.AddStudentCoursesRq;
import ru.seliverstov.userservice.dto.StudentRs;
import ru.seliverstov.userservice.mapper.StudentMapper;
import ru.seliverstov.userservice.model.Course;
import ru.seliverstov.userservice.model.Student;
import ru.seliverstov.userservice.repository.CourseRepository;
import ru.seliverstov.userservice.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;


    public List<StudentRs> getAll() {
        return studentRepository.findAll()
            .stream()
            .map(studentMapper::toStrudentRs)
            .toList();
    }

    public StudentRs postStudent(AddStudentCoursesRq request) {
        Student student = Student.builder()
            .id(request.getStudentId())
            .build();
        /*for (Long i :request.getCourseIds())
        {
            Course course = courseRepository.findById(i)
                .orElseThrow(RuntimeException::new);
            student.toCourse(course);
        }*/

        studentRepository.save(student);
        return studentMapper.toStrudentRs(student);
    }


}
