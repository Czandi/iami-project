package com.app.iami.service;

import com.app.iami.controller.dto.CourseDto;
import com.app.iami.controller.mapper.CourseMapper;
import com.app.iami.model.*;
import com.app.iami.payload.request.CheckingFormRequest;
import com.app.iami.payload.request.CourseRequest;
import com.app.iami.payload.request.GradeRequest;
import com.app.iami.payload.response.CourseResponse;
import com.app.iami.payload.response.TeacherResponse;
import com.app.iami.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final CheckingFormService checkingFormService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;


    public CourseService(CourseRepository courseRepository,
                         CheckingFormService checkingFormService,
                         StudentService studentService,
                         TeacherService teacherService,
                         SubjectService subjectService) {
        this.courseRepository = courseRepository;
        this.checkingFormService = checkingFormService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    public List<CourseDto> getCourses() {

        List<Course> courses = courseRepository.findAll();

        List<CourseDto> courseDtos = CourseMapper.mapToCourseDtos(courses);

        return courseDtos;
    }

    public CourseResponse insertCourse(CourseRequest newCourse) {
        Teacher teacher = teacherService.findById(newCourse.getIdTeacher());

        Subject subject = subjectService.findById(newCourse.getIdSubject());

        Set<Student> students = new HashSet<>();
        for(int i = 0; i < newCourse.getIdStudents().size(); i++){
            Integer id = newCourse.getIdStudents().get(i);
            Student student = studentService.findById(id);
            students.add(student);
        }

        String name = newCourse.getName();
        Integer day = newCourse.getDay();

        Course course = Course.builder()
                .name(name)
                .teacher(teacher)
                .subject(subject)
                .day(day)
                .checkingForms(null)
                .students(students)
                .build();

        course = courseRepository.save(course);
        Set<CheckingForm> checkingForms = new HashSet<>();

        for(int i = 0; i < newCourse.getCheckingForms().size(); i++){
            CheckingFormRequest cf = newCourse.getCheckingForms().get(i);
            CheckingForm checkingForm = checkingFormService.insertCheckingForm(cf, course);
            checkingForms.add(checkingForm);
        }

        course.setCheckingForms(checkingForms);

        TeacherResponse teacherResponse = new TeacherResponse(course.getTeacher());

        CourseResponse courseResponse = CourseResponse.builder()
                .id(course.getId())
                .checkingForms(course.getCheckingForms())
                .day(course.getDay())
                .name(course.getName())
                .students(course.getStudents())
                .subject(course.getSubject())
                .teacher(teacherResponse)
                .build();

        return courseResponse;
    }
}
