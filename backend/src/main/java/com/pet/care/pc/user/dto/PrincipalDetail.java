package com.pet.care.pc.user.dto;

import com.pet.care.pc.entitiy.UserInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
public class PrincipalDetail implements UserDetails, OAuth2User {

  private UserInfo user;
  private Map<String, Object> attributes;

  //생성자

  //일반 로그인
  public PrincipalDetail(UserInfo user) {
    this.user = user;
  }

  //OAuth 로그인
  public PrincipalDetail(UserInfo user, Map<String, Object> attributes) {
    this.user = user;
    this.attributes = attributes;
  }

  //OAuth2User의 메서드
  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  //별로 안중요 안씀
  @Override
  public String getName() {
    return null;
  }

  //UserDetails의 메서드
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collect = new ArrayList<>();
    collect.add(
      new GrantedAuthority() {
        @Override
        public String getAuthority() {
          return user.getRole().toString();
        }
      }
    );
    return collect;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getNickname();
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'isAccountNonExpired'"
    );
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'isAccountNonLocked'"
    );
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'isCredentialsNonExpired'"
    );
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
  }
  //... 아래는 생략 그냥 오버라이드해서 getPassword같이 구현만 함

}
