package com.app.iami.service;

import com.app.iami.model.Student;
import com.app.iami.payload.request.StudentRequest;
import com.app.iami.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student findById(Integer idStudent) {
        return studentRepository.findById(idStudent).orElseThrow();
    }

    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findByNameAndSurname(String name, String surname){
        return studentRepository.findByNameAndSurname(name, surname);
    }
}
