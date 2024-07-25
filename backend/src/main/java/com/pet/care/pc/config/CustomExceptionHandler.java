package com.pet.care.pc.config;

import com.pet.care.pc.dto.api.CommonResponse;
import com.pet.care.pc.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<CommonResponse<Object>> handleCustomException(
    CustomException e
  ) {
    return ResponseEntity
      .status(e.getErrorCode().getHttpStatus())
      .body(
        CommonResponse
          .builder()
          .resCode(e.getErrorCode().getHttpStatus().value())
          .message(e.getMessage())
          .error(e.getErrorCode().getMessage())
          .build()
      );
  }
}
