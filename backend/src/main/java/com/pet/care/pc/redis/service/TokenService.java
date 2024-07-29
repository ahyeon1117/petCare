package com.pet.care.pc.redis.service;

import com.pet.care.pc.dao.repository.redis.TokenRepository;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.redis.entity.JwtToken;
import com.pet.care.pc.redis.jwt.JwtTokenProvider;
import com.pet.care.pc.redis.jwt.JwtTokenVerify;
import com.pet.care.pc.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

  public JwtToken generateJwtToken(String userIdString, String role) {
    String[] userIdArr = Utils.splitUnderscore(userIdString);
    UserId userId = UserId
      .builder()
      .userId(userIdArr[0])
      .platform(userIdArr[1])
      .build();
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

  public String resolveToken(HttpServletRequest request) {
    return tokenProvider.resolveToken(request);
  }

  public JwtTokenVerify verifyToken(String token) {
    return tokenProvider.verifyToken(token);
  }

  public Authentication getAuthentication(String token) {
    return tokenProvider.getAuthentication(token);
  }

  public String getUid(String token) {
    return tokenProvider.getUid(token);
  }

  public String generateTokenFromRefreshToken(String token) {
    return tokenProvider.generateTokenFromRefreshToken(token);
  }
}
