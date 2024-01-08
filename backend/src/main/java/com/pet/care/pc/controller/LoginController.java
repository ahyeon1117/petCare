package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @GetMapping("/login")
  public ResponseEntity<Response> oauth2Callback(
    @RequestParam(name = "code") String code,
    @RequestParam(name = "state") String state,
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    return new ResponseEntity<Response>(
      Response
        .builder()
        .message("loginSuccess")
        .error(null)
        .resCode(HttpStatus.ACCEPTED.value())
        .build(),
      HttpStatus.valueOf(200)
    );
  }

  @GetMapping("/login/oauth2/code/naver")
  public void loginSuccess(
    @RequestParam(name = "accessToken") String accessToken,
    HttpServletRequest request,
    HttpServletResponse response
  ) {
    HttpSession session = request.getSession();
    response.setHeader("Authorization", "Bearer " + accessToken);
  }

  private String getStateFromSession() {
    // 세션에서 상태를 가져오는 로직
    // ...
    return "generated_state"; // 예시로 하드코딩된 값
  }
}
