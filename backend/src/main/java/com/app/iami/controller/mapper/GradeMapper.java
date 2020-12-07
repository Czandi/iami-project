package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.GradeDto;
import com.app.iami.controller.dto.UpdatedGradeDto;
import com.app.iami.model.Grade;
import com.app.iami.payload.response.GradeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GradeMapper {

    static public List<GradeDto> mapToGradeDto(List<Grade> grades){
        List<GradeDto> gradeDtos = new ArrayList<>();

        if(grades.size() > 0){

            for(int i = 0; i < grades.size(); i++){

                Grade currentGrade = grades.get(i);

                GradeResponse gradeResponse = GradeResponse.builder()
                        .id(currentGrade.getId())
                        .name(currentGrade.getGrade())
                        .build();


                GradeDto gradeDto = GradeDto.builder()
                        .grade(gradeResponse)
                        .idCheckingForm(currentGrade.getCheckingForm().getId())
                        .build();

                gradeDtos.add(gradeDto);
            }

        }

        return gradeDtos;
    }

    public static List<UpdatedGradeDto> mapToUpdatedGradeDtos(List<Grade> grades){
        return grades.stream().map(grade -> mapToUpdatedGradeDto(grade)).collect(Collectors.toList());
    }

    public static UpdatedGradeDto mapToUpdatedGradeDto(Grade grade){
        return UpdatedGradeDto.builder()
                .id(grade.getId())
                .checkingForm(CheckingFormMapper.mapToCheckingFormDto(grade.getCheckingForm()))
                .grade(grade.getGrade())
                .build();
    }

}
