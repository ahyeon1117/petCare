package com.pet.care.pc.repository;

import com.pet.care.pc.entitiy.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {}
