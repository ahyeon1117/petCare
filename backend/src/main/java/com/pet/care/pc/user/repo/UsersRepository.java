package com.pet.care.pc.user.repo;

import com.pet.care.pc.user.entity.UserInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserInfo, Long> {
  Optional<UserInfo> findByEmailAndPlatform(String email, String platform);
}
