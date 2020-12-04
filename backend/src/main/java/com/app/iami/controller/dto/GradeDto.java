package com.app.iami.controller.dto;

import com.app.iami.payload.response.GradeResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {

    private Integer idCheckingForm;
    private List<GradeResponse> grades;

}
