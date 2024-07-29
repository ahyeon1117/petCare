package com.pet.care.pc.config;

import com.pet.care.pc.redis.jwt.JwtTokenFilter;
import com.pet.care.pc.security.oauth.filters.RequestFilter;
import com.pet.care.pc.security.oauth.service.CustomOAuth2UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Autowired
  private CustomOAuth2UserService oAuth2UserService;

  private final RequestFilter requestFilter;

  @Autowired
  private JwtTokenFilter jwtTokenFilter;

  private static final String[] AUTH_WHITELIST = {
    "/",
    "/user-info",
    "/index.html",
    "/js/**",
    "/css/**",
    "/error",
    "/html/**",
    "/fonts/**",
    "/images/**",
    "/oauth2/**",
    "/swagger-ui/**",
    "/api-docs/**",
  };

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .formLogin(login -> login.loginProcessingUrl("/login") // .loginPage("/login/page")
      // .defaultSuccessUrl("/") // 성공시 리다이렉
      )
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      )
      .authorizeHttpRequests(request ->
        request
          .requestMatchers("/user/**")
          .authenticated()
          .requestMatchers("/manager")
          .hasAnyRole("ADMIN", "MANAGER")
          .requestMatchers("/admin")
          .hasRole("ADMIN")
          .requestMatchers(AUTH_WHITELIST)
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .formLogin(formLogin -> formLogin.loginPage("/"))
      .oauth2Login(login ->
        login
          .loginPage("/")
          .successHandler(requestFilter)
          .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
      )
      .logout(logout ->
        logout
          .logoutUrl("logout")
          .logoutSuccessUrl("/")
          .deleteCookies("JESSIONID")
      )
      .cors(cors -> cors.configurationSource(corsConfigurationSource()));

    http.addFilterBefore(
      jwtTokenFilter,
      UsernamePasswordAuthenticationFilter.class
    );
    return http.build();
  }

  private CorsConfigurationSource corsConfigurationSource() {
    // Very permissive CORS config...
    final var configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setExposedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    // Limited to API routes (neither actuator nor Swagger-UI)
    final var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
