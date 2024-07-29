package com.pet.care.pc.redis.service;

import com.pet.care.pc.dao.repository.redis.TokenRepository;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.redis.entity.JwtToken;
import com.pet.care.pc.redis.jwt.JwtTokenProvider;
import com.pet.care.pc.utils.Utils;

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

  public JwtToken generateJwtToken(String token, String role) {
    String[] userIdArr = Utils.splitUnderscore(tokenProvider.getUid(token));
    UserId userId = UserId.builder().userId(userIdArr[0]).platform(userIdArr[1]).build();
    String refreshToken = tokenProvider.generateRefreshToken(userId, role);
    String accessToken = tokenProvider.generateAccessToken(userId, role);

    JwtToken result = new JwtToken(
      accessToken,
      userId.getUserId(),
      userId.getPlatform(),
      refreshToken
    );

    save(result);

    return result;
  }
}
