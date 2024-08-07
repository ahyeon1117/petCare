package com.pet.care.pc.security.oauth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
  USER("ROLE_USER"),
  MANAGER("ROLE_MANAGER"),
  ADMIN("ROLE_ADMIN");

  private final String key;
}
