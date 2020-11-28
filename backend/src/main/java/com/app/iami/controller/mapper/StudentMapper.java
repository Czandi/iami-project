package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.GradeDto;
import com.app.iami.controller.dto.StudentDto;
import com.app.iami.model.Grade;
import com.app.iami.model.Student;

import java.util.List;

public class StudentMapper {

    static public StudentDto mapToStudentDto(Student student, List<GradeDto> grades){
        return StudentDto.builder()
                .id(student.getId())
                .surname(student.getSurname())
                .name(student.getName())
                .grades(grades)
                .build();
    }

}
