package com.pet.care.pc.dto.api;

import com.pet.care.pc.entitiy.user.Users;
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

  private Users user;
  private long petId;
  private String age;
  private String gender;
  private String kind;
  private Boolean neutering;
  private String hospital;
  private String caution;
  private String imgPath;
  private AnimalKind animalKind;
  private Integer tendencyKindId;
}
