package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.dto.AddCourseRatingRq;
import ru.seliverstov.userservice.dto.AddCourseRatingRs;
import ru.seliverstov.userservice.dto.AddTaskEstimationRq;
import ru.seliverstov.userservice.dto.TaskEstimationRs;
import ru.seliverstov.userservice.services.CourseRatingService;

import java.util.List;

@RestController
@RequestMapping("api/v1/course-rating")
@RequiredArgsConstructor
public class CourseRatingController {
    private final CourseRatingService courseRatingService;
    @GetMapping("all")
    public List<AddCourseRatingRs> getAll() {
        return courseRatingService.getAll();
    }

    //http://loclahost:8085/api/v1/course-rating?userId=1&taskId=1
    @GetMapping
    public AddCourseRatingRs getCourseRatingById(@RequestParam final Long studentId,
                                                 @RequestParam final Long courseId) {
        return courseRatingService.getById(studentId, courseId);
    }

    @PostMapping
    public AddCourseRatingRs CourseRating(@RequestBody final AddCourseRatingRq request) {
        return courseRatingService.postCourseRating(request);
    }

}
