package com.app.iami.model.imodel;

import com.app.iami.controller.dto.TeacherDto;

public interface ICourse {

    Integer getId();
    String getName();
    TeacherDto getTeacher();


}
