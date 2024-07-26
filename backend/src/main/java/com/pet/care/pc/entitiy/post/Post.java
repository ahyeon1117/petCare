package com.pet.care.pc.entitiy.post;

import com.pet.care.pc.entitiy.user.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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
  @JoinColumns(
    {
      @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
      @JoinColumn(name = "platform", referencedColumnName = "platform"),
    }
  )
  private Users user;

  private Long like;
  private String createAt;
}
