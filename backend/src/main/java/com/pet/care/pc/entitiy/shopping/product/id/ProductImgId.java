package com.pet.care.pc.entitiy.shopping.product.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgId implements Serializable {

  private Long productId;
  private Integer page;
}
