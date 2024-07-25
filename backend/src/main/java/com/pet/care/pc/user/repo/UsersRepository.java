package com.pet.care.pc.user.repo;

import com.pet.care.pc.entitiy.user.Users;
import com.pet.care.pc.entitiy.user.id.UserId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, UserId> {
  Optional<Users> findByEmailAndPlatform(String email, String platform);
}
