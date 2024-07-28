package com.pet.care.pc.dao.repository;

import com.pet.care.pc.entitiy.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {}
