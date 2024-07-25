package com.pet.care.pc.redis.repository;

import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.redis.entity.JwtToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<JwtToken, UserId> {
  void deleteByAccessToken(String accessToken);
}
