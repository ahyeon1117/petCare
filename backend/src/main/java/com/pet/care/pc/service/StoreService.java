package com.pet.care.pc.service;

import com.pet.care.pc.dao.mapper.StoreMapper;
import com.pet.care.pc.dao.repository.StoreRepository;
import com.pet.care.pc.entitiy.shopping.Store;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.utils.Utils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private StoreMapper mapper;

  public Store save(Store store) {
    return storeRepository.save(store);
  }

  public List<Store> findByAll() {
    return storeRepository.findAll();
  }

  public List<Store> findByUserId(String param) {
    UserId userId = Utils.converUserId(param);
    return mapper.findByUserId(userId.getUserId(), userId.getPlatform());
  }
}
