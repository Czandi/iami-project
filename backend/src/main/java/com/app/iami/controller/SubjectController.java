package com.app.iami.controller;

import com.app.iami.model.Subject;
import com.app.iami.service.SubjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }

    @PostMapping("/subjects")
    public Subject insertSubject(@RequestBody String name) { return subjectService.insertSubject(name);
    }
}
