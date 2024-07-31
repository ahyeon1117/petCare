package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.CommonResponse;
import com.pet.care.pc.dto.api.req.StoreSaveRequest;
import com.pet.care.pc.entitiy.shopping.Store;
import com.pet.care.pc.enums.ResponseStatus;
import com.pet.care.pc.redis.jwt.JwtTokenProvider;
import com.pet.care.pc.service.StoreService;
import com.pet.care.pc.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

  @Autowired
  private StoreService service;

  @Autowired
  private JwtTokenProvider jwtUtils;

  @PostMapping
  public ResponseEntity<CommonResponse<String>> save(
    @RequestBody StoreSaveRequest req
  ) {
    try {
      Store store = new Store();
      BeanUtils.copyProperties(req, store);
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

  public ResponseEntity<CommonResponse<List<Store>>> findByUser(
    HttpServletRequest httpServletRequest
  ) {
    try {
      String userId = jwtUtils.getUid(
        jwtUtils.resolveToken(httpServletRequest)
      );
      List<Store> data = service.findByUserId(userId);
      int resCode = ResponseUtils.getResCode(data);
      return new ResponseEntity<>(
        new CommonResponse<List<Store>>(resCode, data, null),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @GetMapping("all")
  public ResponseEntity<CommonResponse<List<Store>>> findAll() {
    try {
      List<Store> data = service.findByAll();
      int resCode = ResponseUtils.getResCode(data);
      return new ResponseEntity<>(
        new CommonResponse<List<Store>>(resCode, data, null),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
