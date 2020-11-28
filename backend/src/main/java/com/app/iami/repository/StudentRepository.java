package com.app.iami.repository;

import com.app.iami.model.Course;
import com.app.iami.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByNameAndSurname(String name, String surname);

    List<Student> findByCourses(Course course);
}
