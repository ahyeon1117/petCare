package com.pet.care.pc.controller;

import com.pet.care.pc.user.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
public class UserController {

  @PostMapping("/register")
  public void postMethodName(@RequestBody UserInfo entity) {
    //TODO: process POST request
  }

  @GetMapping("/info")
  public UserInfo getMethodName(@RequestParam String token) {
    return UserInfo.builder().build();
  }
}
