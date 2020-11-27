package com.app.iami.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GradeRequest {

    private Integer idStudent;
    private Integer idCheckingForm;
    private String grade;

}
