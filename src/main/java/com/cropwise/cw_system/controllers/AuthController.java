package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.user.JwtResponse;
import com.cropwise.cw_system.dtos.user.UserRequest;
import com.cropwise.cw_system.dtos.user.UserResponse;
import com.cropwise.cw_system.security.jwt.JwtService;
import com.cropwise.cw_system.services.UserService;
import com.cropwise.cw_system.security.CustomUserDetail;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        UserRequest userRequestWithRolByDefault = new UserRequest(userRequest.name(), userRequest.email(), userRequest.password(), userRequest.role());

        UserResponse userResponse = userService.createUser(userRequestWithRolByDefault);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.name(), userRequest.password()));

        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetail);

        JwtResponse jwtResponse = new JwtResponse(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
