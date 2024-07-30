package com.pet.care.pc.entitiy.shopping;

import com.pet.care.pc.entitiy.shopping.id.StoreImgId;
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
@IdClass(StoreImgId.class)
public class StoreImg {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private Store store;

  @Id
  private Integer page;

  private String imgPath;
}
