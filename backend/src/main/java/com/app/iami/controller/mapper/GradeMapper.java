package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.GradeDto;
import com.app.iami.model.Grade;

import java.util.List;
import java.util.stream.Collectors;

public class GradeMapper {

    public static List<GradeDto> mapToGradeDtos(List<Grade> grades){
        return grades.stream().map(grade -> mapToGradeDto(grade)).collect(Collectors.toList());
    }

    public static GradeDto mapToGradeDto(Grade grade) {
        return GradeDto.builder()
                .id(grade.getId())
                .checkingForm(grade.getCheckingForm())
                .grade(grade.getGrade())
                .build();
    }
}
