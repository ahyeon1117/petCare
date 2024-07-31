package com.pet.care.pc.entitiy.shopping.product;

import com.pet.care.pc.entitiy.shopping.product.id.ProductImgId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  private Product product;

  @Id
  @Column(name = "product_id")
  private Long productId;

  @Id
  private Integer page;

  private String imgPath;
}
