package com.app.iami.controller.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PresenceDto {

    private Integer id;
    private LocalDate date;
    private Boolean presence;

}
