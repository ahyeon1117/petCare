package com.pet.care.pc.security.jwt;

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

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
    try {
      if (Boolean.TRUE.equals(jwtTokenProvider.verifyToken(token).getValid())) {
        Authentication auth = token != null
          ? jwtTokenProvider.getAuthentication(token)
          : null;
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (ExpiredJwtException e) {
      handleExpiredToken(request, response, "", filterChain);
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
      refreshToken != null &&
      jwtTokenProvider.verifyToken(refreshToken).getValid()
    ) {
      String newAccessToken = jwtTokenProvider.generateTokenFromRefreshToken(
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
