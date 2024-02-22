package com.pet.care.pc.utils;

import java.util.List;

public class ResponseUtils {

  public static int getResCode(List<Object> list) {
    return list.size() == 0 ? 202 : 200;
  }

  public static int getResCode(Object obj) {
    return obj == null ? 202 : 200;
  }
}
