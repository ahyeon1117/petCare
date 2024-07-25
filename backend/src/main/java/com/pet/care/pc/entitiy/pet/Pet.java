package com.pet.care.pc.entitiy.pet;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.enums.AnimalKind;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pet {

  @ManyToOne
  @JoinColumns(
    {
      @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
      @JoinColumn(name = "platform", referencedColumnName = "platform"),
    }
  )
  private Users user;

  @Id
  private long petId;

  private String age;
  private String gender;
  private String kind;
  private Boolean neutering;
  private String hospital;
  private String caution;
  private String imgPath;

  @Enumerated(EnumType.STRING)
  private AnimalKind animalKind;

  @CreatedDate
  private LocalDateTime createAt;

  @LastModifiedDate
  private LocalDateTime modifyAt;
}
