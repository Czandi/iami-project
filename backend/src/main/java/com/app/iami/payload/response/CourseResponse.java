package com.app.iami.payload.response;

import com.app.iami.model.CheckingForm;
import com.app.iami.model.Student;
import com.app.iami.model.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class CourseResponse {

    private Integer id;
    private String name;
    private TeacherResponse teacher;
    private Subject subject;
    private Set<Student> students;
    private Set<CheckingForm> checkingForms;
    private Integer day;
    private String time;

}
