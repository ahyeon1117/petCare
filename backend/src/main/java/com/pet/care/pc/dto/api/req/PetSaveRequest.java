package com.pet.care.pc.dto.api.req;

import com.pet.care.pc.enums.AnimalKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetSaveRequest {

  private String name;
  private Integer age;
  private String gender;
  private String kind;
  private Boolean neutering;
  private String hospital;
  private String caution;
  private String img;
  private AnimalKind animalKind;
  private Integer tendencyKindId;
}
