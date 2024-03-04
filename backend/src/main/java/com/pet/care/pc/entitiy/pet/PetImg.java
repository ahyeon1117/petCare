package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PetImg {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "pet_img_seq_gen")
  private Long seq;

  private Long petId;

  private String imgPath;
}
