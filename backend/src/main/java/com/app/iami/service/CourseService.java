package com.app.iami.service;

import com.app.iami.controller.dto.CourseDto;
import com.app.iami.controller.dto.GradeDto;
import com.app.iami.controller.dto.PresenceDto;
import com.app.iami.controller.dto.StudentDto;
import com.app.iami.controller.mapper.CourseMapper;
import com.app.iami.controller.mapper.GradeMapper;
import com.app.iami.controller.mapper.PresenceMapper;
import com.app.iami.controller.mapper.StudentMapper;
import com.app.iami.model.*;
import com.app.iami.payload.request.*;
import com.app.iami.payload.response.CourseResponse;
import com.app.iami.payload.response.TeacherResponse;
import com.app.iami.repository.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final CheckingFormService checkingFormService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GradeService gradeService;
    private final PresenceService presenceService;

    public CourseService(CourseRepository courseRepository,
                         CheckingFormService checkingFormService,
                         StudentService studentService,
                         TeacherService teacherService,
                         SubjectService subjectService, GradeService gradeService, PresenceService presenceService) {
        this.courseRepository = courseRepository;
        this.checkingFormService = checkingFormService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.gradeService = gradeService;
        this.presenceService = presenceService;
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

    public List<GradeDto> getAllGradesFromCourse(Integer id) {

        Course course = findById(id);

        List<Grade> grades = gradeService.findGradesForCourse(course);

        List<GradeDto> gradesDto = GradeMapper.mapToGradeDtos(grades);

        return gradesDto;
    }

    public List<GradeDto> insertGradesForStudent(Integer idCourse, List<GradeRequest> grades) {
        return grades.stream().map(grade -> insertGradeForStudent(idCourse, grade)).collect(Collectors.toList());
    }

    public GradeDto insertGradeForStudent(Integer id, GradeRequest gradeRequest) {

        Course course = courseRepository.findById(id).orElseThrow();
        CheckingForm checkingForm = checkingFormService.findById(gradeRequest.getIdCheckingForm());
        Student student = studentService.findById(gradeRequest.getIdStudent());
        String gradeValue = gradeRequest.getGrade();

        Grade g = Grade.builder()
                .course(course)
                .checkingForm(checkingForm)
                .student(student)
                .grade(gradeValue)
                .build();

        Grade grade = gradeService.insertGrade(g);

        return GradeMapper.mapToGradeDto(grade);
    }

    public List<Student> insertStudents(Integer id, List<StudentRequest> students) {
        return students.stream().map(student -> insertStudent(id, student)).collect(Collectors.toList());
    }

    public Student insertStudent(Integer id, StudentRequest s) {

        Course course = courseRepository.findById(id).orElseThrow();

        String name = s.getName();
        String surname = s.getSurname();

        Student student = studentService.findByNameAndSurname(name, surname);

        if(student == null){
            student = Student.builder()
                    .name(name)
                    .surname(surname)
                    .courses(null)
                    .build();
            student = studentService.insertStudent(student);
        }

        Set<Student> students = course.getStudents();

        students.add(student);

        courseRepository.save(course);

        return student;
    }

    public List<StudentDto> getStudents(Integer idCourse) {

        Course course = findById(idCourse);

        List<Student> students = studentService.getStudentsByCourse(course);

        List<StudentDto> studentDtos = students.stream().map(student -> {
            List<Grade> grades = gradeService.findByStudentAndCourse(student, course);
            return StudentMapper.mapToStudentDto(student, GradeMapper.mapToGradeDtos(grades));
        }).collect(Collectors.toList());

        return studentDtos;
    }

    public Course findById(Integer id){
        return courseRepository.findById(id).orElseThrow();
    }

    public List<PresenceDto> getAllPresences(Integer idCourse) {

        Course course = findById(idCourse);

        List<Presence> presences = presenceService.findByCourse(course);

        return PresenceMapper.mapToPresenceDtos(presences);
    }

    public PresenceDto insertPresenceForStudent(Integer idCourse, PresenceRequest p) {

        Course course = findById(idCourse);
        Student student = studentService.findById(p.getIdStudent());

        Presence presence = Presence.builder()
                .presence(p.getPresence())
                .course(course)
                .student(student)
                .date(LocalDate.now())
                .build();

        presence = presenceService.insertPresence(presence);

        return PresenceMapper.mapToPresenceDto(presence);
    }

    public List<PresenceDto> insertPresencesForStudents(Integer idCourse, List<PresenceRequest> presences) {
        return presences.stream().map(presence -> insertPresenceForStudent(idCourse, presence)).collect(Collectors.toList());
    }
}
