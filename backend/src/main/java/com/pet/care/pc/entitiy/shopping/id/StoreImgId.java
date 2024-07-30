package com.pet.care.pc.entitiy.shopping.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreImgId implements Serializable {

  private Long storeId;
  private Integer page;
}
