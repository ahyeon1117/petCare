package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.Response;
import com.pet.care.pc.entitiy.UserInfo;
import com.pet.care.pc.enums.ErrorCode;
import com.pet.care.pc.exception.CustomException;
import com.pet.care.pc.security.jwt.JwtTokenProvider;
import com.pet.care.pc.user.enums.Platform;
import com.pet.care.pc.user.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/register")
  private void postMethodName(@RequestBody UserInfo entity) {
    //TODO: process POST request
  }

  @GetMapping("/info")
  private ResponseEntity<Response<UserInfo>> getMethodName(
    @RequestParam String email,
    Platform platform
  ) throws NotFoundException {
    return new ResponseEntity<Response<UserInfo>>(
      Response
        .<UserInfo>builder()
        .message(
          userService
            .findByEmailAndPlatform(email, platform.toString())
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND))
        )
        .error(null)
        .resCode(HttpStatus.ACCEPTED.value())
        .build(),
      HttpStatusCode.valueOf(200)
    );
  }

  // @GetMapping("/login")
  // public ResponseEntity<Response> login(@RequestParam String code)
  //   throws NotFoundException {
  //   return new ResponseEntity<Response>(
  //     Response
  //       .builder()
  //       .message(jwtTokenProvider.generateAccessToken(code, code) )
  //       .error(null)
  //       .resCode(HttpStatus.ACCEPTED.value())
  //       .build(),
  //     HttpStatusCode.valueOf(200)
  //   );
  // }

  @GetMapping("/user-info")
  private ResponseEntity<Response<Map<String, Object>>> getUserInfo(
    OAuth2AuthenticationToken authenticationToken
  ) {
    OAuth2User oAuth2User = (OAuth2User) authenticationToken.getPrincipal();
    return new ResponseEntity<Response<Map<String, Object>>>(
      Response
        .<Map<String, Object>>builder()
        .message(oAuth2User.getAttributes())
        .error(null)
        .resCode(HttpStatus.ACCEPTED.value())
        .build(),
      HttpStatus.valueOf(200)
    );
  }
}
