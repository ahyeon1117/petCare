package com.pet.care.pc.entitiy.user;

import com.pet.care.pc.entitiy.pet.Pet;
import com.pet.care.pc.entitiy.post.Post;
import com.pet.care.pc.entitiy.shopping.order.Orders;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.user.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserId.class)
@EntityListeners(AuditingEntityListener.class)
public class Users {

  @Id
  private String userId;

  @Id
  private String platform;

  private String email;

  @OneToOne(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  private Follows follows;

  @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  private List<Pet> pets;

  @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  private List<Post> posts;

  @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  private List<Orders> orders;

  private String passwd;

  private String name;

  @CreatedDate
  private LocalDateTime createDate;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Role role;
}
