package com.app.iami.payload.response;

import com.app.iami.model.Teacher;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

    private Integer id;
    private String name;
    private String surname;

    public TeacherResponse(Teacher teacher){
        this.setId(teacher.getId());
        this.setSurname(teacher.getSurname());
        this.setName(teacher.getName());
    }

}
