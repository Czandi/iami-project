package com.app.iami.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CourseRequest {

    private String name;
    private Integer idTeacher;
    private Integer idSubject;
    private Integer day;
    private List<Integer> idStudents;
    private List<CheckingFormRequest> checkingForms;

}
