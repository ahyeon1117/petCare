package com.pet.care.pc.entitiy.order;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class Orders {

  @Id
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;

  private LocalDateTime createdAt;
  private StatusEnum status;
}
