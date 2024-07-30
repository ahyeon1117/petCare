package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.CommonResponse;
import com.pet.care.pc.entitiy.shopping.Store;
import com.pet.care.pc.enums.ResponseStatus;
import com.pet.care.pc.service.StoreService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

  @Autowired
  private StoreService service;

  @PostMapping
  public ResponseEntity<CommonResponse<String>> save(@RequestBody Store store) {
    try {
      service.save(store);
      return new ResponseEntity<>(
        new CommonResponse<String>(
          200,
          ResponseStatus.SUCCESS.toString(),
          null
        ),
        HttpStatusCode.valueOf(200)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
