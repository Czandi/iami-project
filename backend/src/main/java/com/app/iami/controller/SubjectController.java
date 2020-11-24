package com.app.iami.controller;

import com.app.iami.model.Subject;
import com.app.iami.service.SubjectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }
}
