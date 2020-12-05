package com.app.iami.service;

import com.app.iami.controller.dto.PresenceDto;
import com.app.iami.controller.mapper.PresenceMapper;
import com.app.iami.model.Course;
import com.app.iami.model.Presence;
import com.app.iami.model.Student;
import com.app.iami.payload.request.UpdatePresenceRequest;
import com.app.iami.repository.PresenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<LocalDate> findAllDatesInCourse(Course course) {
        return presenceRepository.findAllDatesByCourse(course);
    }

    public List<Presence> findByStudentAndCourseOrderByDate(Student student, Course course) {
        return presenceRepository.findByStudentAndCourseOrderByDate(student, course);
    }

    public Boolean deleteByCourseAndDate(Course course, LocalDate date) {

        List<Presence> presences = presenceRepository.findByCourseAndDate(course, date);

        if(presences.size() > 0){
            for(int i = 0; i < presences.size(); i++){
                presenceRepository.delete(presences.get(i));
            }

            return true;
        }

        return false;
    }

    public Presence findById(Integer id){
        return presenceRepository.findById(id).orElseThrow();
    }

    public List<PresenceDto> updatePresences(List<UpdatePresenceRequest> presences) {

        List<Presence> newPresences =  presences.stream().map(presence -> {
            Presence newPresence = findById(presence.getId());
            newPresence.setPresence(presence.getPresence());
            presenceRepository.save(newPresence);
            return newPresence;
        }).collect(Collectors.toList());

        return PresenceMapper.mapToPresenceDtos(newPresences);
    }
}
