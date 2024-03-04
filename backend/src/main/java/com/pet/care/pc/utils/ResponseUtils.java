package com.pet.care.pc.utils;

import com.pet.care.pc.dto.api.Response;
import java.util.List;

public class ResponseUtils {

  public static int getResCode(List<Object> list) {
    return list.size() == 0 ? 202 : 200;
  }

  public static int getResCode(Object obj) {
    return obj == null ? 202 : 200;
  }

  public static Response<?> response(String error, Object data) {
    int resCode = ResponseUtils.getResCode(data);
    return new Response<>(resCode, data, error);
  }
}
