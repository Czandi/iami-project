package com.app.iami.service;

import com.app.iami.model.Course;
import com.app.iami.model.Presence;
import com.app.iami.model.Student;
import com.app.iami.repository.PresenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PresenceService {

    private final PresenceRepository presenceRepository;

    public PresenceService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    public List<Presence> findByCourse(Course course){
        return presenceRepository.findByCourseOrderByDate(course);
    }

    public Presence insertPresence(Presence presence) {
        return presenceRepository.save(presence);
    }

    public List<LocalDate> findAllDates() {
        return presenceRepository.findAllDates();
    }

    public List<Presence> findByStudentAndCourseOrderByDate(Student student, Course course) {
        return presenceRepository.findByStudentAndCourseOrderByDate(student, course);
    }
}
