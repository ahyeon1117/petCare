package com.pet.care.pc.dao.mapper;

import com.pet.care.pc.entitiy.shopping.Store;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {
  List<Store> findByUserId(
    @Param("userId") String userId,
    @Param("platform") String platform
  );
}
