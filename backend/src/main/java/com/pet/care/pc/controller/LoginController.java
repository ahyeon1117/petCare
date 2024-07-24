package com.pet.care.pc.controller;

import com.pet.care.pc.user.dto.PrincipalDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
  private RedirectView oauthLoginSuccess(
    HttpServletRequest req,
    HttpServletResponse res
  ) {
    return new RedirectView("/");
  }
}
