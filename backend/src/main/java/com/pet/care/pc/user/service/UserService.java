package com.pet.care.pc.user.service;

import com.pet.care.pc.user.entity.UserInfo;
import com.pet.care.pc.user.repo.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UsersRepository usersRepo;

  public Optional<UserInfo> findByEmailAndPlatform(
    String email,
    String platform
  ) {
    return usersRepo.findByEmailAndPlatform(email, platform);
  }

  public void save(UserInfo userInfo) {
    usersRepo.save(userInfo);
  }
}
