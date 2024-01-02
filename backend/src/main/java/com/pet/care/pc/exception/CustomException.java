package com.pet.care.pc.exception;

import com.pet.care.pc.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

  ErrorCode errorCode;
}
