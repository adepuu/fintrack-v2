package com.adepuu.fintrackv2.auth.dto;

import com.adepuu.fintrackv2.auth.entity.Token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
  private Token accessToken;
  private Token refreshToken;
}
