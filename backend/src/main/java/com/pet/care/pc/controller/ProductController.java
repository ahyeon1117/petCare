package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.CommonResponse;
import com.pet.care.pc.dto.api.res.SaveResponse;
import com.pet.care.pc.entitiy.shopping.product.Product;
import com.pet.care.pc.service.ProductService;
import com.pet.care.pc.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

  @Autowired
  private ProductService service;

  @Hidden
  @PostMapping
  @Operation(summary = "상품 정보 조회", description = "상품 정보 조회")
  public ResponseEntity<CommonResponse<SaveResponse>> saveAll(
    @RequestBody List<Product> products
  ) {
    return new ResponseEntity<>(
      new CommonResponse<SaveResponse>(
        200,
        SaveResponse.builder().build(),
        null
      ),
      HttpStatusCode.valueOf(200)
    );
    // try {
    //   Products data = service.findById(id);
    //   int resCode = ResponseUtils.getResCode(data);

    //   return new ResponseEntity<>(
    //     new CommonResponse<Products>(resCode, data, null),
    //     HttpStatusCode.valueOf(resCode)
    //   );
    // } catch (Exception e) {
    //   throw e;
    // }
  }

  @Hidden
  @GetMapping(value = "all")
  @Operation(
    summary = "모든 상품 정보 조회",
    description = "모든 상품 정보 조회"
  )
  public ResponseEntity<CommonResponse<List<Product>>> findAll() {
    try {
      List<Product> data = service.findAll();
      int resCode = ResponseUtils.getResCode(data);

      return new ResponseEntity<>(
        new CommonResponse<List<Product>>(resCode, data, null),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @Hidden
  @GetMapping(value = "/all/search")
  @Operation(summary = "상품 정보 조회", description = "상품 정보 조회")
  public ResponseEntity<CommonResponse<Product>> findAll(
    @RequestParam("id") Long id
  ) {
    try {
      Product data = service.findById(id);
      int resCode = ResponseUtils.getResCode(data);

      return new ResponseEntity<>(
        new CommonResponse<Product>(resCode, data, null),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
