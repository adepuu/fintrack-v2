package com.adepuu.fintrackv2.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adepuu.fintrackv2.common.Response;

@ControllerAdvice
public class AuthExceptionHandlers {
  @ExceptionHandler(LoginFailedException.class)
  public ResponseEntity<?> handleLoginFailedException(LoginFailedException e) {
    return Response.failedResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
  }

  @ExceptionHandler(DuplicateUserException.class)
  public ResponseEntity<?> handleDuplicateUserException(DuplicateUserException e) {
    return Response.failedResponse(HttpStatus.CONFLICT.value(), e.getMessage());
  }
}
