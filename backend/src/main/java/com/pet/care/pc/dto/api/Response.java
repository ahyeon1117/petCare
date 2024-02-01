package com.pet.care.pc.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

  private int resCode;
  private T message;
  private String error;

  @SuppressWarnings("unchecked")
  public Response<T> success(HttpStatus status, T message) {
    return (Response<T>) Response
      .builder()
      .resCode(status.value())
      .message(message)
      .build();
  }
}
