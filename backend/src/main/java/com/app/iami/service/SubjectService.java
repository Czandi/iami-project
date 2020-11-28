package com.app.iami.service;

import com.app.iami.model.Subject;
import com.app.iami.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public Subject findById(Integer idSubject) {
        return subjectRepository.findById(idSubject).orElseThrow();
    }

    public Subject insertSubject(String name) {
        Subject subject = Subject.builder()
                .name(name)
                .build();

        return subjectRepository.save(subject);
    }
}
