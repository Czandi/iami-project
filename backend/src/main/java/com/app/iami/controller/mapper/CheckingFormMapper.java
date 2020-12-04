package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.CheckingFormDto;
import com.app.iami.model.CheckingForm;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckingFormMapper {

    public static List<CheckingFormDto> mapToCheckingFormDtos(Set<CheckingForm> checkingForms){
        return checkingForms.stream().map(checkingForm -> mapToCheckingFormDto(checkingForm)).collect(Collectors.toList());
    }

    private static CheckingFormDto mapToCheckingFormDto(CheckingForm checkingForm) {
        return CheckingFormDto.builder()
                .id(checkingForm.getId())
                .name(checkingForm.getName())
                .weight(checkingForm.getWeight())
                .build();
    }

}
