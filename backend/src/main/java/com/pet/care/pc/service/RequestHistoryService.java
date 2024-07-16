package com.pet.care.pc.service;

import com.pet.care.pc.entitiy.RequestHistory;
import com.pet.care.pc.repository.jpa.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RequestHistoryService {

  @Autowired
  private RequestHistoryRepository repository;

  public void save(RequestHistory value) throws Exception {
    repository.save(value);
  }
}
