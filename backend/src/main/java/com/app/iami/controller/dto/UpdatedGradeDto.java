package com.app.iami.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UpdatedGradeDto {

    private Integer id;
    private CheckingFormDto checkingForm;
    private String grade;

}
