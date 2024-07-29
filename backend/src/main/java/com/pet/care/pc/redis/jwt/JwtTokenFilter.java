package com.pet.care.pc.redis.jwt;

import com.pet.care.pc.redis.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;

  public JwtTokenFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws IOException, ServletException {
    String token = tokenService.resolveToken((HttpServletRequest) request);
    try {
      if (Boolean.TRUE.equals(tokenService.verifyToken(token).getValid())) {
        Authentication auth = token != null
          ? tokenService.getAuthentication(token)
          : null;
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (ExpiredJwtException e) {
      handleExpiredToken(
        request,
        response,
        tokenService.getUid(token),
        filterChain
      );
    }
    filterChain.doFilter(request, response);
  }

  private void handleExpiredToken(
    HttpServletRequest request,
    HttpServletResponse response,
    String refreshToken,
    FilterChain chain
  ) throws IOException, ServletException {
    if (
      refreshToken != null && tokenService.verifyToken(refreshToken).getValid()
    ) {
      String newAccessToken = tokenService.generateTokenFromRefreshToken(
        refreshToken
      );
      response.setHeader("Authorization", "Bearer " + newAccessToken);
      // Optionally, you can also update the security context with the new token
    } else {
      response.sendError(
        HttpServletResponse.SC_UNAUTHORIZED,
        "JWT Token has expired and refresh token is invalid"
      );
    }
  }
}
