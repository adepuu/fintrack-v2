package com.adepuu.fintrackv2.auth.controller;

import com.adepuu.fintrackv2.auth.dto.LoginRequest;
import com.adepuu.fintrackv2.auth.dto.RegisterRequest;
import com.adepuu.fintrackv2.auth.entity.User;
import com.adepuu.fintrackv2.auth.mapper.RegisterRequestToUserEntity;
import com.adepuu.fintrackv2.auth.service.UserService;
import com.adepuu.fintrackv2.common.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    User registeredUser = userService.registerUser(RegisterRequestToUserEntity.map(request));
    return Response.successfulResponse(
            "User registered successfully",
            registeredUser
    );
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    return Response.successfulResponse(
            "User logged in successfully",
            userService.loginUser(request.getEmail(), request.getPassword())
    );
  }
} 
