package com.pet.care.pc.security.oauth.filters;

import com.pet.care.pc.security.jwt.JwtToken;
import com.pet.care.pc.security.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

// @Slf4j
@Component
@RequiredArgsConstructor
public class RequestFilter extends SimpleUrlAuthenticationSuccessHandler {

  // private final JwtTokenProvider jwtUtil;
  private final OAuth2AuthorizedClientService authorizedClientService;

  @Override
  public void onAuthenticationSuccess(
    HttpServletRequest request,
    HttpServletResponse response,
    Authentication authentication
  ) throws IOException, ServletException {
    // OAuth2User로 캐스팅하여 인증된 사용자 정보를 가져온다.
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    // // 사용자 이메일을 가져온다.
    // String email = oAuth2User.getAttribute("email");
    // // 서비스 제공 플랫폼(GOOGLE, KAKAO, NAVER)이 어디인지 가져온다.
    // String provider = oAuth2User.getAttribute("provider");

    String registrationId = request.getParameter("registrationId");
    OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
      registrationId,
      authentication.getName()
    );
    // CustomOAuth2UserService에서 셋팅한 로그인한 회원 존재 여부를 가져온다.
    boolean isExist = oAuth2User.getAttribute("exist");

    // OAuth2User로 부터 Role을 얻어온다.
    // String role = oAuth2User
    //   .getAuthorities()
    //   .stream()
    //   .findFirst() // 첫번째 Role을 찾아온다.
    //   .orElseThrow(IllegalAccessError::new) // 존재하지 않을 시 예외를 던진다.
    //   .getAuthority(); // Role을 가져온다.

    // 회원이 존재할경우
    if (isExist) {
      // 회원이 존재하면 jwt token 발행을 시작한다.
      // JwtToken token = jwtUtil.generateToken(email, role);
      // log.info("jwtToken = {}", token.getAccessToken());

      // accessToken을 쿼리스트링에 담는 url을 만들어준다.
      if (authorizedClient != null) {
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        // 세션에 토큰 저장
        request
          .getSession()
          .setAttribute("accessToken", accessToken.getTokenValue());
      }

      super.onAuthenticationSuccess(request, response, authentication);
    }
    //  else {
    //   // 회원이 존재하지 않을경우, 서비스 제공자와 email을 쿼리스트링으로 전달하는 url을 만들어준다.
    //   String targetUrl = UriComponentsBuilder
    //     .fromUriString("http://localhost:1117/register")
    //     .queryParam("email", (String) oAuth2User.getAttribute("email"))
    //     .queryParam("provider", provider)
    //     .queryParam(
    //       "accessToken",
    //       authorizedClient.getAccessToken().getTokenValue()
    //     )
    //     .build()
    //     .encode(StandardCharsets.UTF_8)
    //     .toUriString();
    //   // 회원가입 페이지로 리다이렉트 시킨다.
    //   getRedirectStrategy().sendRedirect(request, response, targetUrl);
    // }
  }
}
