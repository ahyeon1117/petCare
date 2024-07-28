package com.pet.care.pc.security.oauth.service;

import com.pet.care.pc.dao.repository.UsersRepository;
import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.security.oauth.dto.PrincipalDetail;
import com.pet.care.pc.utils.Utils;
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
    String[] userIdArr = Utils.splitUnderscore(userId);
    Optional<Users> userEntity = usersRepo.findById(
      UserId.builder().userId(userIdArr[0]).platform(userIdArr[1]).build()
    );
    PrincipalDetail principalDetail = userEntity.isPresent() == true
      ? new PrincipalDetail(userEntity.get())
      : null;
    return principalDetail;
  }
}
