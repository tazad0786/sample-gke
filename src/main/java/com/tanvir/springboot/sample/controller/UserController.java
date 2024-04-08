package com.tanvir.springboot.sample.controller;

import com.tanvir.springboot.sample.model.*;
import com.tanvir.springboot.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

   // @HasEndpointAuthorities(authorities = { SecurityAuthorities.USER })
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    //@HasEndpointAuthorities(authorities = { SecurityAuthorities.ADMIN })
    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody CreateUserRequest request) {
        User newUser = userService.createUser(UserMapper.fromCreateRequest(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(newUser.getId())
                                .toUri();
        return ResponseEntity.created(location).body(UserMapper.toDto(newUser));
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @Validated @RequestBody UpdateUserRequest request) {
        User updatedUser = userService.updateUser(id, UserMapper.fromUpdateRequest(request));
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserMapper.toDto(updatedUser));
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
