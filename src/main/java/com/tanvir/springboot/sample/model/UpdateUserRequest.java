package com.tanvir.springboot.sample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
}
