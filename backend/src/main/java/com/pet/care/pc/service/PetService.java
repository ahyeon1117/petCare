package com.pet.care.pc.service;

import com.pet.care.pc.entitiy.pet.Pet;
import com.pet.care.pc.repository.PetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetService {

  @Autowired
  private PetRepository repository;

  public List<Pet> findAll() {
    return repository.findAll();
  }

  public Pet findByPetId(Long petId) {
    return repository.findById(petId).orElseThrow();
  }

  public Pet save(Pet pet) {
    return repository.save(pet);
  }
}
