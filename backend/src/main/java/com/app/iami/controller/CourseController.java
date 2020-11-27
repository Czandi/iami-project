package com.app.iami.controller;

import com.app.iami.controller.dto.CourseDto;
import com.app.iami.model.CheckingForm;
import com.app.iami.model.Course;
import com.app.iami.model.Grade;
import com.app.iami.payload.request.CourseRequest;
import com.app.iami.payload.request.GradeRequest;
import com.app.iami.payload.response.CourseResponse;
import com.app.iami.service.CheckingFormService;
import com.app.iami.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    private final CheckingFormService checkingFormService;

    public CourseController(CourseService courseService, CheckingFormService checkingFormService) {
        this.courseService = courseService;
        this.checkingFormService = checkingFormService;
    }

    @GetMapping("/courses")
    public List<CourseDto> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping("/courses")
    public CourseResponse insertCourse(@RequestBody CourseRequest newCourse){
        return courseService.insertCourse(newCourse);
    }

}
