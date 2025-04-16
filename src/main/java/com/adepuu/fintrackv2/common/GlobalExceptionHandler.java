package com.adepuu.fintrackv2.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException(Exception e) {
    return Response.failedResponse(500, e.getMessage());
  }
}
