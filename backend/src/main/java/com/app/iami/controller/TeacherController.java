package com.app.iami.controller;

import com.app.iami.model.Teacher;
import com.app.iami.service.TeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }
}
