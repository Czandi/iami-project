package com.app.iami.controller.dto;

import com.app.iami.model.CheckingForm;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {

    private Integer id;
    private CheckingForm checkingForm;
    private String grade;

}
