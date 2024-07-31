package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(
  name = "pet_disease_seq_gen",
  sequenceName = "pet_disease_seq",
  initialValue = 1,
  allocationSize = 1
)
public class PetDisease {

  @Id
  @GeneratedValue(
    strategy = GenerationType.AUTO,
    generator = "pet_disease_seq_gen"
  )
  private Long id;

  private String name;
}
