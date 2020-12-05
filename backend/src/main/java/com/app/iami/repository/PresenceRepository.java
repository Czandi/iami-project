package com.app.iami.repository;

import com.app.iami.model.Course;
import com.app.iami.model.Presence;
import com.app.iami.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    List<Presence> findByCourseOrderByDate(Course course);

    @Query("Select distinct p.date from Presence p " +
            "where p.course like ?1")
    List<LocalDate> findAllDatesByCourse(Course course);

    List<Presence> findByStudentAndCourseOrderByDate(Student student, Course course);

    List<Presence> findByCourseAndDate(Course course, LocalDate date);

}
