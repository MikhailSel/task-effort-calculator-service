package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.dto.AddStudentCoursesRq;
import ru.seliverstov.userservice.dto.StudentRs;
import ru.seliverstov.userservice.dto.TaskRegistrationRq;
import ru.seliverstov.userservice.dto.TaskRs;
import ru.seliverstov.userservice.model.Student;
import ru.seliverstov.userservice.services.StudentService;
import ru.seliverstov.userservice.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentRs> getStudentList() {return studentService.getAll();}

    @PostMapping
    public StudentRs postStudent(@RequestBody final AddStudentCoursesRq request){
        return studentService.postStudent(request);
    }
}
