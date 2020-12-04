package com.app.iami.repository;

import com.app.iami.model.Course;
import com.app.iami.model.Grade;
import com.app.iami.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByCourse(Course course);

    List<Grade> findByStudentAndCourseOrderByCheckingForm(Student student, Course course);
}
