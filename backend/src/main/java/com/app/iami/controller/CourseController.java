package com.app.iami.controller;

import com.app.iami.controller.dto.*;
import com.app.iami.controller.mapper.CourseMapper;
import com.app.iami.model.Course;
import com.app.iami.model.Grade;
import com.app.iami.model.Presence;
import com.app.iami.model.Student;
import com.app.iami.payload.request.*;
import com.app.iami.payload.response.CourseResponse;
import com.app.iami.payload.response.PresenceResponse;
import com.app.iami.service.CheckingFormService;
import com.app.iami.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/courses/{id}/grades")
    public List<Grade> insertGradeForStudent(@PathVariable("id") Integer idCourse, @RequestBody List<GradeRequest> grades){
        return courseService.insertGradesForStudent(idCourse, grades);
    }

    @PostMapping(value = "/courses/{id}/students", params = "student")
    public Student insertStudent(@PathVariable Integer id, @RequestBody StudentRequest student){
        return courseService.insertStudent(id, student);
    }

    @PostMapping(value = "/courses/{id}/presences")
    public List<Presence> insertPresencesForStudents(@PathVariable("id") Integer idCourse, @RequestBody List<PresenceRequest> presences){
        return courseService.insertPresencesForStudents(idCourse, presences);
    }

    @GetMapping("/courses/{id}/students-data")
    public StudentDataDto getStudentsData(@PathVariable("id") Integer idCourse){
        return courseService.getStudentsData(idCourse);
    }

    @GetMapping("courses/{id}")
    public CourseDto getCoursesById(@PathVariable("id") Integer idCourse) {
        return CourseMapper.mapToCourseDto(courseService.findById(idCourse));
    }

    @DeleteMapping("courses/{id}/presences")
    public Boolean deletePresences(@PathVariable("id") Integer idCourse, @RequestBody String date){
        return courseService.deletePresences(idCourse, date);
    }

    @PutMapping("courses/{id}/presences")
    public List<PresenceDto> updatePresences(@PathVariable("id") Integer idCourse, @RequestBody List<UpdatePresenceRequest> presences){
        return courseService.updatePresences(presences);
    }

//    @GetMapping("/courses/{id}/grades")
//    public List<GradeDto> getAllGrades(@PathVariable("id") Integer idCourse){
//        return courseService.getAllGradesFromCourse(idCourse);
//    }

//    @PostMapping(value = "/courses/{id}/grades", params = "grade")
//    public GradeDto insertGradeForStudent(@PathVariable("id") Integer idCourse, @RequestBody GradeRequest grade){
//        return courseService.insertGradeForStudent(idCourse, grade);
//    }

//    @GetMapping("/courses/{id}/students")
//    public List<StudentDto> getStudentList(@PathVariable("id") Integer idCourse){
//        return courseService.getStudents(idCourse);
//    }

//    @PostMapping("/courses/{id}/students")
//    public List<Student> insertStudents(@PathVariable Integer id, @RequestBody List<StudentRequest> students){
//        return courseService.insertStudents(id, students);
//    }

//    @GetMapping("/courses/{id}/presences")
//    public List<PresenceDto> getPresences(@PathVariable("id") Integer idCourse){
//        return courseService.getAllPresences(idCourse);
//    }

//    @PostMapping(value = "/courses/{id}/presences")
//    public PresenceDto insertPresenceForStudent(@PathVariable("id") Integer idCourse, @RequestBody PresenceRequest presence){
//        return courseService.insertPresenceForStudent(idCourse, presence);
//    }
}
