package com.pet.care.pc.repository;

import com.pet.care.pc.entitiy.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHistoryRepository
  extends JpaRepository<RequestHistory, Long> {}
