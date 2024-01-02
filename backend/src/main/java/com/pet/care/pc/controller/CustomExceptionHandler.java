package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.Response;
import com.pet.care.pc.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<Response> handleCustomException(CustomException e) {
    return ResponseEntity
      .status(e.getErrorCode().getHttpStatus())
      .body(
        Response
          .builder()
          .resCode(e.getErrorCode().getHttpStatus().value())
          .message(e.getMessage())
          .error(e.getErrorCode().getMessage())
          .build()
      );
  }
}
