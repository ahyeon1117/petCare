package com.pet.care.pc.user.repo;

import com.pet.care.pc.user.entity.UserInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Long, UserInfo> {
  Optional<UserInfo> findByEmailAndPlatform(String email, String platform);
  void save(UserInfo userInfo);
}
