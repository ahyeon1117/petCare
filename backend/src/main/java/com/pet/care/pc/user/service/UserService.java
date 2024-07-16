package com.pet.care.pc.user.service;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.user.dto.PrincipalDetail;
import com.pet.care.pc.user.repo.UsersRepository;
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
  public UserDetails loadUserByUsername(String id)
    throws UsernameNotFoundException {
    Optional<Users> userEntity = usersRepo.findById(Long.parseLong(id));
    PrincipalDetail principalDetail = userEntity.isPresent() == true
      ? new PrincipalDetail(userEntity.get())
      : null;
    return principalDetail;
  }
}
