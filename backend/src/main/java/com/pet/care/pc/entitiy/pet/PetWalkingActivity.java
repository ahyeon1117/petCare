package com.pet.care.pc.entitiy.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@SequenceGenerator(
  name = "pet_walking_activity_seq_gen",
  sequenceName = "pet_disease_seq",
  initialValue = 1,
  allocationSize = 1
)
@EntityListeners(AuditingEntityListener.class)
public class PetWalkingActivity {

  @Id
  @GeneratedValue(
    strategy = GenerationType.AUTO,
    generator = "pet_disease_seq_gen"
  )
  private Long id;

  @CreatedDate
  private LocalDateTime createDateTime;
}
