package com.pet.care.pc.redis.service;

import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.redis.entity.JwtToken;
import com.pet.care.pc.redis.jwt.JwtTokenProvider;
import com.pet.care.pc.redis.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository refreshTokenRepository;
  private final JwtTokenProvider tokenProvider;

  public void save(JwtToken jwtToken) {
    refreshTokenRepository.save(jwtToken);
  }

  public void deleteAccessToken(String accessToken) {
    refreshTokenRepository.deleteByAccessToken(accessToken);
  }

  public JwtToken generateJwtToken(UserId id, String role) {
    String refreshToken = tokenProvider.generateRefreshToken(
      id.getUserId(),
      id.getPlatform(),
      role
    );
    String accessToken = tokenProvider.generateAccessToken(
      id.getUserId(),
      id.getPlatform(),
      role
    );

    JwtToken result = new JwtToken(
      accessToken,
      id.getUserId(),
      id.getPlatform(),
      refreshToken
    );

    save(result);

    return result;
  }
}
