package com.pet.care.pc.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  public static final String defaultPattern = "yyyy-MM-dd HH:mm:ss.SSS";

  public static String getCurrDefaultDateTime() {
    return LocalDateTime
      .now()
      .format(DateTimeFormatter.ofPattern(DateUtils.defaultPattern))
      .toString();
  }
}
