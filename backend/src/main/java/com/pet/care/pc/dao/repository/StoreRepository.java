package com.pet.care.pc.dao.repository;

import com.pet.care.pc.entitiy.shopping.Store;
import com.pet.care.pc.entitiy.user.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
  Optional<Store> findByUser(Users user);
}
