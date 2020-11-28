package com.app.iami.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private Integer id;
    private String name;
    private String surname;
    private List<GradeDto> grades;

}
