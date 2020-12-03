package com.app.iami.repository;

import com.app.iami.model.Course;
import com.app.iami.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    List<Presence> findByCourseOrderByDate(Course course);

}
