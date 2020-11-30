package com.app.iami.controller;

import com.app.iami.controller.dto.CourseDto;
import com.app.iami.controller.dto.GradeDto;
import com.app.iami.controller.dto.PresenceDto;
import com.app.iami.controller.dto.StudentDto;
import com.app.iami.model.Student;
import com.app.iami.payload.request.CourseRequest;
import com.app.iami.payload.request.GradeRequest;
import com.app.iami.payload.request.PresenceRequest;
import com.app.iami.payload.request.StudentRequest;
import com.app.iami.payload.response.CourseResponse;
import com.app.iami.service.CheckingFormService;
import com.app.iami.service.CourseService;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/courses/{id}/grades")
//    public List<GradeDto> getAllGrades(@PathVariable("id") Integer idCourse){
//        return courseService.getAllGradesFromCourse(idCourse);
//    }

//    @PostMapping(value = "/courses/{id}/grades", params = "grade")
//    public GradeDto insertGradeForStudent(@PathVariable("id") Integer idCourse, @RequestBody GradeRequest grade){
//        return courseService.insertGradeForStudent(idCourse, grade);
//    }

    @PostMapping("/courses/{id}/grades")
    public List<GradeDto> insertGradeForStudent(@PathVariable("id") Integer idCourse, @RequestBody List<GradeRequest> grades){
        return courseService.insertGradesForStudent(idCourse, grades);
    }

    @GetMapping("/courses/{id}/students")
    public List<StudentDto> getStudentList(@PathVariable("id") Integer idCourse){
        return courseService.getStudents(idCourse);
    }

//    @PostMapping(value = "/courses/{id}/students", params = "student")
//    public Student insertStudent(@PathVariable Integer id, @RequestBody StudentRequest student){
//        return courseService.insertStudent(id, student);
//    }

    @PostMapping("/courses/{id}/students")
    public List<Student> insertStudents(@PathVariable Integer id, @RequestBody List<StudentRequest> students){
        return courseService.insertStudents(id, students);
    }

    @GetMapping("/courses/{id}/presences")
    public List<PresenceDto> getPresences(@PathVariable("id") Integer idCourse){
        return courseService.getAllPresences(idCourse);
    }

//    @PostMapping(value = "/courses/{id}/presences")
//    public PresenceDto insertPresenceForStudent(@PathVariable("id") Integer idCourse, @RequestBody PresenceRequest presence){
//        return courseService.insertPresenceForStudent(idCourse, presence);
//    }

    @PostMapping(value = "/courses/{id}/presences")
    public List<PresenceDto> insertPresencesForStudents(@PathVariable("id") Integer idCourse, @RequestBody List<PresenceRequest> presences){
        return courseService.insertPresencesForStudents(idCourse, presences);
    }
}