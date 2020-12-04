package com.app.iami.payload.response;

import com.app.iami.controller.dto.GradeDto;
import com.app.iami.controller.dto.PresenceDto;
import com.app.iami.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class StudentDataResponse {

    private Student student;
    private List<PresenceDto> presences;
    private List<GradeDto> grades;

}
