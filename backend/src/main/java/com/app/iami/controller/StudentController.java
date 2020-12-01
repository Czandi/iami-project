package com.app.iami.controller;

import com.app.iami.model.Student;
import com.app.iami.payload.request.StudentRequest;
import com.app.iami.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/students")
    public Student insertStudent(@RequestBody StudentRequest student){
        return studentService.insertNewStudent(student);
    }

}
