package com.pet.care.pc.security.oauth.service;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.security.oauth.dto.OAuth2AttributeDto;
import com.pet.care.pc.user.dto.PrincipalDetail;
import com.pet.care.pc.user.enums.Role;
import com.pet.care.pc.user.service.UserService;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserService userService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest)
    throws OAuth2AuthenticationException {
    // OAuth2UserService를 사용하여 OAuth2User 정보를 가져온다.
    OAuth2User oAuth2User = super.loadUser(userRequest);
    userRequest.getAccessToken();
    // 클라이언트 등록 ID(google, naver, kakao)와 사용자 이름 속성을 가져온다.
    String registrationId = userRequest
      .getClientRegistration()
      .getRegistrationId();
    String userNameAttributeName = userRequest
      .getClientRegistration()
      .getProviderDetails()
      .getUserInfoEndpoint()
      .getUserNameAttributeName();

    // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2AttributeDto 객체를 만든다.
    OAuth2AttributeDto oAuth2Attribute = OAuth2AttributeDto.of(
      registrationId,
      userNameAttributeName,
      oAuth2User.getAttributes()
    );

    // OAuth2AttributeDto의 속성값들을 Map으로 반환 받는다.
    Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

    // 사용자 email(또는 id) 정보를 가져온다.
    String email = (String) memberAttribute.get("email");
    String name = (String) memberAttribute.get("name");
    String platform = (String) memberAttribute.get("platform");

    // 이메일로 가입된 회원인지 조회한다.
    Optional<Users> optionalUser = userService.findByEmailAndPlatform(
      email,
      platform
    );
    Users user;

    if (!optionalUser.isPresent()) {
      // 회원이 존재하지 않을 경우 회원을 생성한다.
      user =
        Users
          .builder()
          .email(email)
          .name(name)
          .id(email) // 로그인 ID로 사용할 값 설정 필요
          .role(Role.USER)
          .platform(platform)
          .build();
      user = userService.save(user);
    } else {
      user = optionalUser.get();
    }

    // PrincipalDetail 객체를 생성하여 반환한다.
    return new PrincipalDetail(user, memberAttribute);
  }
}
