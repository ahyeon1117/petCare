package com.pet.care.pc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  private final ControllerInterceptor controllerInterceptor;

  public WebMvcConfig(ControllerInterceptor controllerInterceptor) {
    this.controllerInterceptor = controllerInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(controllerInterceptor).addPathPatterns("/**");
  }
}
