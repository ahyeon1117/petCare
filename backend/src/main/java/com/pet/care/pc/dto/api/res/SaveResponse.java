package com.pet.care.pc.dto.api.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveResponse {

  private Integer success;
  private Integer fail;
  private Integer total;
}
