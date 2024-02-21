package com.pet.care.pc.config;

import com.pet.care.pc.entitiy.RequestHistory;
import com.pet.care.pc.service.RequestHistoryService;
import com.pet.care.pc.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerInterceptor implements HandlerInterceptor {

  @Autowired
  private RequestHistoryService service;

  @Override
  public void afterCompletion(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler,
    Exception ex
  ) {}

  @Override
  public boolean preHandle(
    HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse,
    Object o
  ) {
    log.info(
      "{} {}",
      httpServletRequest.getMethod(),
      httpServletRequest.getRequestURI()
    );
    try {
      service.save(
        RequestHistory
          .builder()
          .body(RequestUtils.getBody(httpServletRequest))
          .methods(httpServletRequest.getMethod())
          .url(httpServletRequest.getRequestURL().toString())
          .build()
      );
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public void postHandle(
    HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse,
    Object o,
    ModelAndView modelAndView
  ) throws IOException {}
}
