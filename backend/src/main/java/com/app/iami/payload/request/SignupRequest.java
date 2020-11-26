package com.app.iami.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    private String name;

    private String surname;

    private String username;

    private String password;

}
