package com.pet.care.pc.dto.api;

import com.pet.care.pc.enums.AnimalKind;
import com.pet.care.pc.security.oauth.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetSaveRequest {

  private String userId;
  private Platform platform;
  private long petId;
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
