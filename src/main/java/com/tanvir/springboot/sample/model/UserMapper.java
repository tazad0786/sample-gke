package com.tanvir.springboot.sample.model;

import java.util.UUID;

public class UserMapper {
    public static UserDTO toDto(User user) {
        UserDTO dto = UserDTO.builder().build();
        dto.setId(user.getId().toString());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public static User fromCreateRequest(CreateUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }

    public static User fromUpdateRequest(UpdateUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }
}
