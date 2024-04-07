package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.dto.AddCourseStudentsRq;
import ru.seliverstov.userservice.dto.AddStudentCoursesRq;
import ru.seliverstov.userservice.dto.CourseRs;
import ru.seliverstov.userservice.model.Course;
import ru.seliverstov.userservice.model.Student;
import ru.seliverstov.userservice.services.CourseService;
import ru.seliverstov.userservice.services.StudentService;

import java.util.List;

    @RestController
    @RequestMapping("api/v1/courses")
    @RequiredArgsConstructor
    public class CoursesController {
        private final CourseService courseService;

        @GetMapping
        public List<CourseRs> getCoursesList() {return courseService.getAllCourses();}

        @PostMapping
        public CourseRs postCourse(@RequestBody final AddCourseStudentsRq request){
            return courseService.postCourse(request);
        }
    }
