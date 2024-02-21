package com.pet.care.pc.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
  name = "request_history_seq_gen",
  sequenceName = "request_history_seq",
  initialValue = 1,
  allocationSize = 1
)
@AllArgsConstructor
@Table(
  name = "request_history",
  indexes = @Index(name = "idx_request_history", columnList = "url, created_at")
)
public class RequestHistory {

  @Id
  @GeneratedValue(
    strategy = GenerationType.AUTO,
    generator = "request_history_seq_gen"
  )
  private Long seq;

  private String url;

  private String methods;

  @Column(columnDefinition = "TEXT")
  private String body;

  @CreatedDate
  private LocalDateTime createdAt;
}
