package com.pet.care.pc.controller;

import com.pet.care.pc.user.dto.PrincipalDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/login")
public class LoginController {

  @GetMapping
  private String login(
    Authentication authentication,
    @AuthenticationPrincipal PrincipalDetail userDetails
  ) {
    //방법 1
    log.info("/test/login =============================");
    PrincipalDetail principalDetails = (PrincipalDetail) authentication.getPrincipal();
    log.info("authentication:" + principalDetails.getUser());

    //방법 2
    log.info("userDetails:" + userDetails.getUser());

    return "세션 정보 확인";
  }

  @GetMapping("/oauth2")
  private String oauthLoginSuccess(
    Authentication authentication,
    @AuthenticationPrincipal OAuth2User userDetails
  ) { // DI(의존성 주입)
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // 다운캐스팅
    System.out.println("authentication: " + oAuth2User.getAttributes());
    System.out.println("OAuth2User: " + userDetails.getAttributes());

    return "세션 정보 확인하기";
  }
}
