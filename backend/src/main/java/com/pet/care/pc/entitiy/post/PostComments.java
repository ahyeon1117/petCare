package com.pet.care.pc.entitiy.post;

import com.pet.care.pc.entitiy.user.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Posts posts;

  @ManyToOne(fetch = FetchType.LAZY)
  private Users users;

  @Lob
  private String content;

  @CreatedDate
  private LocalDateTime created_at;
}
