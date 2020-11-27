package com.app.iami.controller.dto;

import com.app.iami.model.CheckingForm;
import com.app.iami.model.Student;
import com.app.iami.model.Subject;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CourseDto {

    private Integer id;
    private String name;
    private TeacherDto teacher;
    private Subject subject;
    private Set<Student> students;
    private Set<CheckingForm> checkingForms;
    private Integer day;

}
