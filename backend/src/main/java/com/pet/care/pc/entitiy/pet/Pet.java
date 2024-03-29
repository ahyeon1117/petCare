package com.pet.care.pc.entitiy.pet;

import com.pet.care.pc.entitiy.user.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Pet {

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;

  @Id
  private long petId;

  private String age;
  private String gender;
}
