package com.app.iami.service;

import com.app.iami.controller.dto.*;
import com.app.iami.controller.mapper.*;
import com.app.iami.model.*;
import com.app.iami.payload.request.*;
import com.app.iami.payload.response.CourseResponse;
import com.app.iami.payload.response.StudentDataResponse;
import com.app.iami.payload.response.TeacherResponse;
import com.app.iami.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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
        String time = newCourse.getTime();

        Course course = Course.builder()
                .name(name)
                .teacher(teacher)
                .subject(subject)
                .day(day)
                .time(time)
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

    public List<UpdatedGradeDto> insertGradesForStudent(Integer idCourse, List<GradeRequest> grades) {
        return grades.stream().map(grade -> insertGradeForStudent(idCourse, grade)).collect(Collectors.toList());
    }

    public UpdatedGradeDto insertGradeForStudent(Integer id, GradeRequest gradeRequest) {

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

        return GradeMapper.mapToUpdatedGradeDto(grade);
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

    public Course findById(Integer id){
        return courseRepository.findById(id).orElseThrow();
    }

    public Presence insertPresenceForStudent(Integer idCourse, PresenceRequest p) {

        Course course = findById(idCourse);
        Student student = studentService.findById(p.getIdStudent());

        Presence presence = Presence.builder()
                .presence(p.getPresence())
                .course(course)
                .student(student)
                .date(LocalDate.now())
                .build();

        presence = presenceService.insertPresence(presence);

        return presence;
    }

    public List<Presence> insertPresencesForStudents(Integer idCourse, List<PresenceRequest> presences) {
        return presences.stream().map(presence -> insertPresenceForStudent(idCourse, presence)).collect(Collectors.toList());
    }

    public StudentDataDto getStudentsData(Integer idCourse) {

        Course course = findById(idCourse);

        List<CheckingFormDto> allCheckingForms = CheckingFormMapper.mapToCheckingFormDtos(course.getCheckingForms());
        List<LocalDate> allDates = presenceService.findAllDatesInCourse(course);

        Set<Student> allStudents = findById(idCourse).getStudents();

        List<StudentDataResponse> studentsData = allStudents.stream().map(student -> {

            List<Grade> studentGrades = gradeService.findByStudentAndCourseOrderByCheckingForm(student, course);
            List<Presence> studentPresences = presenceService.findByStudentAndCourseOrderByDate(student, course);

            Boolean perfectPresence = checkIfStudentHasPerfectPresence(studentPresences);

            Float averageGrade = getAverageGradeBasedOnPresence(studentGrades, perfectPresence);

            StudentDataResponse studentDataResponse = StudentDataResponse.builder()
                    .student(student)
                    .grades(GradeMapper.mapToGradeDto(studentGrades))
                    .presences(PresenceMapper.mapToPresenceDtos(studentPresences))
                    .averageGrade(averageGrade)
                    .build();

            return studentDataResponse;
        }).collect(Collectors.toList());

        StudentDataDto studentDataDto = StudentDataDto.builder()
                .dates(allDates)
                .checkingForms(allCheckingForms)
                .studentsData(studentsData)
                .build();

        return studentDataDto;
    }

    public Float getAverageGradeBasedOnPresence(List<Grade> studentGrades, Boolean perfectPresence){
        Float averageGrade = 0f;
        if(studentGrades.size() > 0) {
            Integer minGrade = Integer.parseInt(studentGrades.get(0).getGrade());
            for (int i = 0; i < studentGrades.size(); i++) {
                Integer grade = Integer.parseInt(studentGrades.get(i).getGrade());
                averageGrade += grade;

                if (grade < minGrade) {
                    minGrade = grade;
                }
            }

            if (perfectPresence && studentGrades.size() - 1 == 1) {
                averageGrade -= minGrade;
                System.out.println(averageGrade);
            } else if (perfectPresence && studentGrades.size() - 1 > 1){
                averageGrade -= minGrade;
                averageGrade /= studentGrades.size();
            } else {
                averageGrade /= studentGrades.size();
            }
        }

        return averageGrade;
    }

    public Boolean checkIfStudentHasPerfectPresence(List<Presence> presences){
        Boolean perfectPresence = true;

        for(int i = 0; i < presences.size(); i++){
            Boolean presence = presences.get(i).isPresence();
            if(presence == false){
                perfectPresence = false;
                break;
            }
        }

        return perfectPresence;
    }

    public Boolean deletePresences(Integer idCourse, String date) {

        Course course = findById(idCourse);

        int indexOfFirstSlash = date.indexOf('-');
        int indexOfSecondSlash = date.indexOf('-', date.indexOf('-') + 1);

        int year = Integer.parseInt(date.substring(0, indexOfFirstSlash));
        int month = Integer.parseInt(date.substring(indexOfFirstSlash + 1, indexOfSecondSlash));
        int day = Integer.parseInt(date.substring(indexOfSecondSlash + 1));

        LocalDate newDate = LocalDate.of(year, month, day);

        return presenceService.deleteByCourseAndDate(course, newDate);
    }

    public List<PresenceDto> updatePresences(List<UpdatePresenceRequest> presences) {
        return presenceService.updatePresences(presences);
    }

    public List<UpdatedGradeDto> updateGrades(List<UpdateGradeRequest> grades) {
        return GradeMapper.mapToUpdatedGradeDtos(gradeService.updateGrades(grades));
    }

//    public List<GradeDto> getAllGradesFromCourse(Integer id) {
//
//        Course course = findById(id);
//
//        List<Grade> grades = gradeService.findGradesForCourse(course);
//
//        List<GradeDto> gradesDto = GradeMapper.mapToGradeDtos(grades);
//
//        return gradesDto;
//    }

//    public List<StudentDto> getStudents(Integer idCourse) {
//
//        Course course = findById(idCourse);
//
//        List<Student> students = studentService.getStudentsByCourse(course);
//
//        List<StudentDto> studentDtos = students.stream().map(student -> {
//            List<Grade> grades = gradeService.findByStudentAndCourse(student, course);
//            return StudentMapper.mapToStudentDto(student, GradeMapper.mapToGradeDtos(grades));
//        }).collect(Collectors.toList());
//
//        return studentDtos;
//    }

//    public List<PresenceDto> getAllPresences(Integer idCourse) {
//
//        Course course = findById(idCourse);
//
//        List<Presence> presences = presenceService.findByCourse(course);
//
//        return PresenceMapper.mapToPresenceDtos(presences);
//    }
}
