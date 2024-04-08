package com.tanvir.springboot.sample.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String Id;
}