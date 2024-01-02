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
public class Response {

  private int resCode;
  private Object message;
  private String error;

  public Response success(HttpStatus status, Object message) {
    return Response.builder().resCode(status.value()).message(message).build();
  }
}
