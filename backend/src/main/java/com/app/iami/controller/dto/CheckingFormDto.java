package com.app.iami.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckingFormDto {

    private Integer id;
    private String name;
    private Float weight;

}
