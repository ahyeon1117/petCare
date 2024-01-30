package com.pet.care.pc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

  @GetMapping("/login/test")
  public String login(@RequestParam("accessToken") String accessToken) {
    log.info("token = {}", accessToken);
    return accessToken;
  }

  @GetMapping("/login/oauth2/code/naver")
  public String loginSuccess(
    Authentication authentication,
    @AuthenticationPrincipal OAuth2User userDetails
  ) { // DI(의존성 주입)
    System.out.println("/test/login =====================");
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // 다운캐스팅
    System.out.println("authentication: " + oAuth2User.getAttributes());
    System.out.println("OAuth2User: " + userDetails.getAttributes());

    return "세션 정보 확인하기";
  }
}
