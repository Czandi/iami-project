package com.app.iami.service;

import com.app.iami.model.Course;
import com.app.iami.model.Grade;
import com.app.iami.model.Student;
import com.app.iami.payload.request.UpdateGradeRequest;
import com.app.iami.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Grade> findByStudentAndCourseOrderByCheckingForm(Student student, Course course) {
        return gradeRepository.findByStudentAndCourseOrderByCheckingForm(student, course);
    }

    public List<Grade> updateGrades(List<UpdateGradeRequest> grades) {
        List<Grade> newGrades = grades.stream().map(grade -> {
            Grade newGrade = findById(grade.getId());
            newGrade.setGrade(grade.getGrade());
            gradeRepository.save(newGrade);
            return newGrade;
        }).collect(Collectors.toList());

        return newGrades;
    }

    public Grade findById(Integer id){
        return gradeRepository.findById(id).orElseThrow();
    }
}
