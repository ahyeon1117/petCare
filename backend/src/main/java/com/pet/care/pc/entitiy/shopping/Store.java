package com.pet.care.pc.entitiy.shopping;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.enums.ApproveStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Store {

  @Id
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns(
    {
      @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
      @JoinColumn(name = "platform", referencedColumnName = "platform"),
    }
  )
  @NotNull
  private Users user;

  private String name;
  private String description;

  @Enumerated(EnumType.STRING)
  private ApproveStatus status = ApproveStatus.WAIT;

  private String url;
}
