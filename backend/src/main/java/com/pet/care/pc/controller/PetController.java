package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/pet")
public class PetController {

  @PostMapping(
    value = "picture",
    produces = "application/json",
    consumes = "multipart/form-data"
  )
  private ResponseEntity<Response<Object>> petPicture(
    @RequestBody MultiValueMap<String, String> data
  ) {
    return null;
  }
}
