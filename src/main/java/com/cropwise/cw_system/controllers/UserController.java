package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.UserRequest;
import com.cropwise.cw_system.dtos.UserResponse;
import com.cropwise.cw_system.models.User;
import com.cropwise.cw_system.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
//responseentity gestiona las solicitudes http
   @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
       return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
        // sin el dto return new ResponseEntity<List<User>>(users, HttpStatus.OK);
       // sin el response return userService.getAllUsers();
    }

    //es la respuesta con el userservice, que pasa al request, y segun el estado create
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED );
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.updateUser(id,userRequest),HttpStatus.OK);
    }
    // falta hacer las excepciones personalizadas para que no lance el 500

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
