package com.app.iami.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresenceRequest {

    private Integer idStudent;
    private Boolean presence;

}
