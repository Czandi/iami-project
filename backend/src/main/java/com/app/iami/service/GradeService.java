package com.app.iami.service;

import com.app.iami.model.CheckingForm;
import com.app.iami.model.Course;
import com.app.iami.model.Grade;
import com.app.iami.model.Student;
import com.app.iami.payload.request.GradeRequest;
import com.app.iami.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> findGradesForCourse(Course course) {
        return gradeRepository.findByCourse(course);
    }

    public Grade insertGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> findByStudentAndCourse(Student student, Course course) {
        return gradeRepository.findByStudentAndCourse(student, course);
    }
}
