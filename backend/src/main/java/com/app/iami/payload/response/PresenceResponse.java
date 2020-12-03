package com.app.iami.payload.response;

import com.app.iami.model.CheckingForm;
import com.app.iami.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class PresenceResponse {

    private Integer id;
    private Student student;
    private Boolean presence;

}
