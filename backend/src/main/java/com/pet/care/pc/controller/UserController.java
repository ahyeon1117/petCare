package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.Response;
import com.pet.care.pc.entitiy.UserInfo;
import com.pet.care.pc.enums.ErrorCode;
import com.pet.care.pc.exception.CustomException;
import com.pet.care.pc.user.enums.Platform;
import com.pet.care.pc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public void postMethodName(@RequestBody UserInfo entity) {
    //TODO: process POST request
  }

  @GetMapping("/info")
  public ResponseEntity<Response> getMethodName(
    @RequestParam String email,
    Platform platform
  ) throws NotFoundException {
    return new ResponseEntity<Response>(
      Response
        .builder()
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
}
