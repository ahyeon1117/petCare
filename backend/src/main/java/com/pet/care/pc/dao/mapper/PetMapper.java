package com.pet.care.pc.dao.mapper;

import com.pet.care.pc.entitiy.pet.Pet;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PetMapper {
  List<Pet> findByUserId(
    @Param("userId") String userId,
    @Param("platform") String platform
  );
}
