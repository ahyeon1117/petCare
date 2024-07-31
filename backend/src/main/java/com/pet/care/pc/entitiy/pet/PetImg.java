package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PetImg {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private Pet pet;

  private String imgPath;
}
