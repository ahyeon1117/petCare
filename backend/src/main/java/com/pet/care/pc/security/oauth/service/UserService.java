package com.pet.care.pc.security.oauth.service;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.repository.UsersRepository;
import com.pet.care.pc.security.oauth.dto.PrincipalDetail;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepo;

  public Optional<Users> findByEmailAndPlatform(String email, String platform) {
    return usersRepo.findByEmailAndPlatform(email, platform);
  }

  public Users save(Users userInfo) {
    return usersRepo.save(userInfo);
  }

  @Override
  public UserDetails loadUserByUsername(String userId)
    throws UsernameNotFoundException {
    Optional<Users> userEntity = usersRepo.findById(
      UserId
        .builder()
        .userId(userId)
        .platform(extractIdFromUsername(userId))
        .build()
    );
    PrincipalDetail principalDetail = userEntity.isPresent() == true
      ? new PrincipalDetail(userEntity.get())
      : null;
    return principalDetail;
  }

  private String extractIdFromUsername(String username) {
    // 예를 들어, username이 "user_1" 형식일 때
    String[] parts = username.split("_");
    return parts[1];
  }
}
