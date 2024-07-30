package com.pet.care.pc.entitiy.shopping.product;

import com.pet.care.pc.entitiy.shopping.product.id.ProductImgId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductImgId.class)
public class ProductImg {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private Products product;

  @Id
  private Integer page;

  private String imgPath;
}
