package com.pet.care.pc.service;

import com.pet.care.pc.dao.mapper.PetMapper;
import com.pet.care.pc.dao.repository.PetRepository;
import com.pet.care.pc.entitiy.pet.Pet;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.utils.Utils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetService {

  @Autowired
  private PetRepository repository;

  @Autowired
  private PetMapper mapper;

  public List<Pet> findAll() {
    return repository.findAll();
  }

  public Pet findByPetId(String petId) {
    return repository.findById(petId).orElseThrow();
  }

  public List<Pet> findByUserId(String param) {
    UserId userId = Utils.converUserId(param);
    return mapper.findByUserId(userId.getUserId(), userId.getPlatform());
  }

  public Pet save(Pet pet) {
    return repository.save(pet);
  }
}
