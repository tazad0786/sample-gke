package com.tanvir.springboot.sample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
}