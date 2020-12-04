package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.GradeDto;
import com.app.iami.model.Grade;
import com.app.iami.payload.response.GradeResponse;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {

    static public List<GradeDto> mapToGradeDto(List<Grade> grades){
        List<GradeDto> gradeDtos = new ArrayList<>();

        if(grades.size() > 0){
            Integer prevId = grades.get(0).getCheckingForm().getId();
            List<GradeResponse> gradeResponses = new ArrayList<>();

            for(int i = 0; i < grades.size(); i++){

                Grade currentGrade = grades.get(i);

                Integer currId = currentGrade.getCheckingForm().getId();

                GradeResponse gradeResponse = GradeResponse.builder()
                        .id(currentGrade.getId())
                        .name(currentGrade.getGrade())
                        .build();

                if(prevId != currId){
                    GradeDto gradeDto = GradeDto.builder()
                            .grades(gradeResponses)
                            .idCheckingForm(prevId)
                            .build();

                    gradeDtos.add(gradeDto);

                    gradeResponses = new ArrayList<>();
                }
                gradeResponses.add(gradeResponse);

            }
        }

        return gradeDtos;
    }

}
