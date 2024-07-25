package com.pet.care.pc.entitiy.pet.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetTendencyId implements Serializable {

  private Long petId;
  private Integer kindId;
}
