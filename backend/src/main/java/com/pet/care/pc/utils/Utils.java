package com.pet.care.pc.utils;

import com.pet.care.pc.entitiy.user.id.UserId;

public class Utils {

  public static String[] splitUnderscore(String param) {
    String[] parts = param.split("_");
    return parts;
  }

  // userId_platform
  public static UserId converUserId(String username) {
    String[] parts = username.split("_");
    return UserId.builder().userId(parts[0]).platform(parts[1]).build();
  }
}
