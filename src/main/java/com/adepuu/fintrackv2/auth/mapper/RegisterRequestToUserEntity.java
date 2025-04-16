package com.adepuu.fintrackv2.auth.mapper;

import com.adepuu.fintrackv2.auth.dto.RegisterRequest;
import com.adepuu.fintrackv2.auth.entity.User;

public class RegisterRequestToUserEntity {
  public static User map(RegisterRequest request) {
    return User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(request.getPassword())
            .pin(request.getPin())
            .build();
  }
}
