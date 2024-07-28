package com.pet.care.pc.utils;

public class Utils {

  public static String[] splitUnderscore(String username) {
    String[] parts = username.split("_");
    return parts;
  }
}
