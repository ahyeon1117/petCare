package com.pet.care.pc.entitiy.post;

import com.pet.care.pc.entitiy.user.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Post {

  @Id
  private long id;

  private String title;
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;

  private String status;
  private String createAt;
}
