package com.pet.care.pc.service;

import com.pet.care.pc.dao.repository.StoreRepository;
import com.pet.care.pc.entitiy.shopping.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  @Autowired
  private StoreRepository storeRepository;

  public Store save(Store store) {
    return storeRepository.save(store);
  }
}
