package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class PetHealth {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private Pet pet;

  private long diseaseId;
  private long walkingActivityId;
}
