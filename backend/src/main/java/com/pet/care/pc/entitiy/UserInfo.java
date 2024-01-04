package com.pet.care.pc.entitiy;

import com.pet.care.pc.user.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(
  of = { "id", "oAuth2Id", "email", "nickname", "introduction", "role" }
)
public class UserInfo {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Long id;

  private String loginId;

  private String password;

  private String provider;

  private String providerId;

  @CreatedDate
  private LocalDateTime createDate;

  @Column(unique = true, nullable = false)
  private String oAuth2Id;

  @Column(unique = true)
  private String email;

  @Column(unique = true, nullable = false)
  private String nickname;

  private String profileImageUrl;

  private String introduction;

  private String platform;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Role role;
}
