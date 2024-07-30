package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.CommonResponse;
import com.pet.care.pc.entitiy.shopping.product.Products;
import com.pet.care.pc.service.ProductService;
import com.pet.care.pc.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

  @Autowired
  private ProductService service;

  @Hidden
  @GetMapping(value = "all")
  @Operation(
    summary = "모든 상품 정보 조회",
    description = "모든 상품 정보 조회"
  )
  public ResponseEntity<CommonResponse<List<Products>>> findAll() {
    try {
      List<Products> data = service.findAll();
      int resCode = ResponseUtils.getResCode(data);

      return new ResponseEntity<>(
        new CommonResponse<List<Products>>(resCode, data, null),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @Hidden
  @GetMapping(value = "/all/search")
  @Operation(summary = "상품 정보 조회", description = "상품 정보 조회")
  public ResponseEntity<CommonResponse<Products>> findAll(
    @RequestParam("id") Long id
  ) {
    try {
      Products data = service.findById(id);
      int resCode = ResponseUtils.getResCode(data);

      return new ResponseEntity<>(
        new CommonResponse<Products>(resCode, data, null),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
