package com.app.iami.controller.dto;

import com.app.iami.payload.response.StudentDataResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class StudentDataDto {

    private List<LocalDate> dates;
    private List<CheckingFormDto> checkingForms;
    private List<StudentDataResponse> studentsData;

}
