package com.pet.care.pc.entitiy.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Follows {

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;

  private long following_user_id;
  private long followed_user_id;
}
