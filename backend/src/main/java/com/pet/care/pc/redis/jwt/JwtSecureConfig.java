package com.pet.care.pc.redis.jwt;

import com.pet.care.pc.redis.service.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecureConfig
  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private TokenService tokenService;

  public JwtSecureConfig(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    JwtTokenFilter customFilter = new JwtTokenFilter(tokenService);
    http.addFilterBefore(
      customFilter,
      UsernamePasswordAuthenticationFilter.class
    );
  }
}
