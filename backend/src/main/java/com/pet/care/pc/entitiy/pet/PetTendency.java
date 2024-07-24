package com.pet.care.pc.entitiy.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.care.pc.entitiy.pet.id.PetTendencyId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class PetTendency {

  @EmbeddedId
  private PetTendencyId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("petId")
  @JoinColumn(name = "pet_id")
  @JsonIgnore
  private Pet pet;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("kindId")
  @JoinColumn(name = "tendency_kind_id")
  @JsonIgnore
  private PetTendencyKind kind;
}
