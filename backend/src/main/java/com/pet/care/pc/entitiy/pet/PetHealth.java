package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PetHealth {

  @Id
  private long petId;

  private long diseaseId;
  private long walking_activity_id;
}
