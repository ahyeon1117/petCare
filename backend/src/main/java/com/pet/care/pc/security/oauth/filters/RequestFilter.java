package com.pet.care.pc.security.oauth.filters;

import com.pet.care.pc.redis.service.TokenService;
import com.pet.care.pc.security.oauth.dto.PrincipalDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestFilter extends SimpleUrlAuthenticationSuccessHandler {

  private final TokenService service;

  private final OAuth2AuthorizedClientService authorizedClientService;

  @Override
  public void onAuthenticationSuccess(
    HttpServletRequest request,
    HttpServletResponse response,
    Authentication authentication
  ) throws IOException, ServletException {
    // OAuth2User로 캐스팅하여 인증된 사용자 정보를 가져온다.
    PrincipalDetail principalDetail = (PrincipalDetail) authentication.getPrincipal();
    String platform = principalDetail.getAttribute("platform");
    String email = principalDetail.getAttribute("email");
    String role = principalDetail.getAttribute("role");
    OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
      principalDetail.getAttribute("platform").toString().toLowerCase(),
      authentication.getName()
    );
    // CustomOAuth2UserService에서 셋팅한 로그인한 회원 존재 여부를 가져온다.

    if (authorizedClient != null) {
      OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

      // 쿠키에 토큰 설정
      Cookie cookie = new Cookie(
        "jwt",
        service
          .generateJwtToken(String.format("%s_%s", email, platform), role)
          .getAccessToken()
      );
      cookie.setHttpOnly(true); // 클라이언트 스크립트에서 접근 불가
      cookie.setSecure(true); // HTTPS에서만 전송 (개발 시에는 false로 설정할 수 있음)
      cookie.setPath("/"); // 애플리케이션 전체에서 사용 가능
      cookie.setMaxAge(
        (int) accessToken.getExpiresAt().getEpochSecond() -
        (int) System.currentTimeMillis() /
        1000
      ); // 토큰 만료 시간 설정
      log.info("Setting cookie: {}", cookie.getValue());
      response.addCookie(cookie);
    }
    if (response.isCommitted()) {
      log.warn("Response has already been committed, unable to set cookie.");
      return;
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
