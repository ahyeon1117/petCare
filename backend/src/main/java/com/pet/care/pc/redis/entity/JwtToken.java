package com.pet.care.pc.redis.entity;

import com.pet.care.pc.entitiy.user.id.UserId;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@IdClass(UserId.class)
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 14440)
public class JwtToken {

  @Id
  private String id;

  @Id
  private String platform;

  private String refreshToken;
  private String accessToken;
}
