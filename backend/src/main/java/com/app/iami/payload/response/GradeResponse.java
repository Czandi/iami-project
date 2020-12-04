package com.app.iami.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GradeResponse {

    private Integer id;
    private String name;

}
