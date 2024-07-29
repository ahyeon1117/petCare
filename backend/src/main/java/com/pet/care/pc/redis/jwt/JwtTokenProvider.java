package com.pet.care.pc.redis.jwt;

import com.pet.care.pc.entitiy.user.id.UserId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${jwt.secretKey}")
  private String secretKey = "secretKey";

  @Value("${jwt.expire-length.accessToken}")
  private Long accessTokenExpire;

  @Value("${jwt.expire-length.refereshToken}")
  private Long refereshTokenExpire;

  SecretKey key = Jwts.SIG.HS512.key().build();

  @PostConstruct
  protected void init() {
    key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }

  @Autowired
  private UserDetailsService userDetailsService;

  public String generateRefreshToken(UserId id, String role) {
    // 토큰의 유효 기간을 밀리초 단위로 설정.
    long refreshPeriod = 1000L * 60L * 60L * 24L * refereshTokenExpire;

    // 새로운 클레임 객체를 생성하고, 이메일과 역할(권한)을 셋팅
    Map<String, String> claims = new HashMap<>();
    claims.put("id", id.getUserId());
    claims.put("platform", id.getPlatform());
    claims.put("role", role);

    // 현재 시간과 날짜를 가져온다.
    Date now = new Date();

    return Jwts
      .builder()
      .subject(String.format("%s_%s", id.getUserId(), id.getPlatform()))
      // Payload를 구성하는 속성들을 정의한다.
      .claims(claims)
      // 발행일자를 넣는다.
      .issuedAt(now)
      // 토큰의 만료일시를 설정한다.
      .expiration(new Date(now.getTime() + refreshPeriod))
      // 지정된 서명 알고리즘과 비밀 키를 사용하여 토큰을 서명한다.
      .signWith(key)
      .compact();
  }

  public String generateAccessToken(UserId id, String role) {
    long tokenPeriod = 1000L * 60L * accessTokenExpire; // 30분
    // 새로운 클레임 객체를 생성하고, 이메일과 역할(권한)을 셋팅
    Map<String, String> claims = new HashMap<>();
    claims.put("id", id.getUserId());
    claims.put("platform", id.getPlatform());
    claims.put("role", role);
    Date now = new Date();
    return Jwts
      .builder()
      .subject(String.format("%s_%s", id.getUserId(), id.getPlatform()))
      //    Payload를 구성하는 속성들을 정의한다.
      .claims(claims)
      // 발행일자를 넣는다.
      .issuedAt(now)
      // 토큰의 만료일시를 설정한다.
      .expiration(new Date(now.getTime() + tokenPeriod))
      // 지정된 서명 알고리즘과 비밀 키를 사용하여 토큰을 서명한다.
      .signWith(key)
      .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails =
      this.userDetailsService.loadUserByUsername(getUid(token));
    return new UsernamePasswordAuthenticationToken(
      userDetails,
      "",
      userDetails.getAuthorities()
    );
  }

  public JwtTokenVerify verifyToken(String token) {
    JwtTokenVerify jwtTokenVerify = new JwtTokenVerify(false, "");
    try {
      Jws<Claims> claims = Jwts
        .parser()
        .verifyWith(key) // 비밀키를 설정하여 파싱한다.
        .build()
        .parseSignedClaims(token); // 주어진 토큰을 파싱하여 Claims 객체를 얻는다.

      // 토큰의 만료 시간과 현재 시간비교
      boolean expired = claims.getPayload().getExpiration().after(new Date());
      // 만료 시간이 현재 시간 이후인지 확인하여 유효성 검사 결과를 반환
      if (!expired) {
        jwtTokenVerify.setErr("different issuer");
      }
      jwtTokenVerify.setValid(true);
    } catch (ExpiredJwtException e) {
      // jwtTokenVerify.setErr("expired token");
      throw e;
    } catch (JwtException | IllegalArgumentException e) {
      jwtTokenVerify.setErr("invalid token");
    }
    return jwtTokenVerify;
  }

  public String getUid(String token) {
    return Jwts
      .parser()
      .verifyWith(key)
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getSubject();
  }

  public UserId getUserId(String token) {
    return UserId
      .builder()
      .platform(get(token, "platform"))
      .userId(get(token, "userId"))
      .build();
  }

  public String get(String token, String jsonKey) {
    return Jwts
      .parser()
      .verifyWith(key)
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getOrDefault(jsonKey, String.class)
      .toString();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }

  public String generateTokenFromRefreshToken(String refreshToken) {
    // Implement token generation from refresh token
    Claims claims = getAllClaimsFromToken(refreshToken);
    return doGenerateToken(claims, claims.getSubject());
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts
      .parser()
      .verifyWith(key)
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts
      .builder()
      .claims(claims)
      .subject(subject)
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(
        new Date(new Date().getTime() + 1000L * 60L * accessTokenExpire)
      )
      .signWith(key)
      .compact();
  }
}
