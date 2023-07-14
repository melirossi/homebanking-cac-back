package com.ar.bankingmelisa.api.controllers;

import com.ar.bankingmelisa.api.dtos.UserDto;
import com.ar.bankingmelisa.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /*
    GET /users
    Retrieve all users.
    @return ResponseEntity<List<UserDto>> - List of UserDto objects as response body.
    */
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = service.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /*
    GET /users/{id}
    Retrieve a user by their ID.
    @param id - ID of the user.
    @return ResponseEntity<UserDto> - UserDto object as response body.
    */
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    /*
    POST /users
    Create a new user.
    @param dto - UserDto object containing user information.
    @return ResponseEntity<UserDto> - Created UserDto object as response body.
    */
    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
    }

    /*
    PUT /users/{id}
    Update an existing user.
    @param id - ID of the user to be updated.
    @param user - Updated UserDto object.
    @return ResponseEntity<UserDto> - Updated UserDto object as response body.
    */
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, user));
    }

    /*
    DELETE /users/{id}
    Delete a user by their ID.
    @param id - ID of the user to be deleted.
    @return ResponseEntity<String> - Response message indicating the result of the operation.
    */
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }

}
