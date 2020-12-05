package com.app.iami.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdatePresenceRequest {

    private Integer id;
    private Boolean presence;

}
