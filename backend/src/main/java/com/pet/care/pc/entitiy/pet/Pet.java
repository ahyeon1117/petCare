package com.pet.care.pc.entitiy.pet;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.enums.AnimalKind;
import com.pet.care.pc.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@SequenceGenerator(
  name = "pet_seq_gen",
  sequenceName = "pet_seq",
  initialValue = 1,
  allocationSize = 1
)
@EntityListeners(AuditingEntityListener.class)
public class Pet {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns(
    {
      @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
      @JoinColumn(name = "platform", referencedColumnName = "platform"),
    }
  )
  @NotNull
  private Users user;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "pet_seq_gen")
  private long petId;

  @NotNull
  private String name;

  @NotNull
  private Integer age;

  @NotNull
  private Gender gender;

  @NotNull
  private Boolean neutering;

  @NotNull
  private String hospital;

  @NotNull
  private String caution;

  private String imgPath;

  private String kind;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AnimalKind animalKind;

  @CreatedDate
  private LocalDateTime createAt;

  @LastModifiedDate
  private LocalDateTime modifyAt;
}
