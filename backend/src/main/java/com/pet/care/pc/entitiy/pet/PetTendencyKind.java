package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PetTendencyKind {

  @Id
  private Integer id;

  private String topic;
  private String description;
}
