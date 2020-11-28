package com.app.iami.controller.dto;

import com.app.iami.model.Student;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PresenceDto {

    private int id;
    private Student student;
    private boolean presence;
    private LocalDate date;

}
