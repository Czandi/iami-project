package com.app.iami.repository;

import com.app.iami.model.Course;
import com.app.iami.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByCourse(Course course);

}
