package com.app.iami.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UpdateGradeRequest {

    private Integer id;
    private String grade;

}
