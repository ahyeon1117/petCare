package com.pet.care.pc.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtTokenVerify {

  private Boolean valid;
  private String err;
}
