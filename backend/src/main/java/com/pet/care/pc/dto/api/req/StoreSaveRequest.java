package com.pet.care.pc.dto.api.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreSaveRequest {

  private String name;

  private String description;
  private String status;

  private String url;
}
