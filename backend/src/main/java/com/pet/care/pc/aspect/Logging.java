package com.pet.care.pc.aspect;

import com.pet.care.pc.utils.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class Logging {

  @Around("execution(* com.pet.care.pc.controller..*(..))")
  public Object controllerAccessLoggging(ProceedingJoinPoint joinPoint)
    throws Throwable {
    StopWatch stopWatch = new StopWatch();

    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    String start = DateUtils.getCurrDefaultDateTime();

    stopWatch.start();
    Object result = joinPoint.proceed(); // 조인포인트의 메서드 실행
    stopWatch.stop();

    String end = DateUtils.getCurrDefaultDateTime();

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();
    log.info(
      "url : {} {} | method : {} || start : {} | end : {} | duration : {}ms ",
      request.getMethod(),
      request.getRequestURL().toString(),
      methodName,
      start,
      end,
      stopWatch.getTotalTimeMillis()
    );

    return result;
  }
}
