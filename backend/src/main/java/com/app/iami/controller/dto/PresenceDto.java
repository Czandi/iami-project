package com.app.iami.controller.dto;

import com.app.iami.model.Student;
import com.app.iami.payload.response.PresenceResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PresenceDto {

    private LocalDate date;
    private List<PresenceResponse> presences;

}
