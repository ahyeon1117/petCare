package com.pet.care.pc.redis.jwt;

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
