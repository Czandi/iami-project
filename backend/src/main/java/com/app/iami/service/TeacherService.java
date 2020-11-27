package com.app.iami.service;

import com.app.iami.model.Teacher;
import com.app.iami.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Integer idTeacher) {
        return teacherRepository.findById(idTeacher).orElseThrow();
    }
}
