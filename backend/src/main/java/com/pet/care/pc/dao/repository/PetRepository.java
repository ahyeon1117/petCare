package com.pet.care.pc.dao.repository;

import com.pet.care.pc.entitiy.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {}
