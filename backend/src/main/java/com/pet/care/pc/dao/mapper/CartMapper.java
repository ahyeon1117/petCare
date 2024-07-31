package com.pet.care.pc.dao.mapper;

import com.pet.care.pc.entitiy.shopping.cart.Cart;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {
  public List<Cart> findByUserId(
    @Param("userId") String userId,
    @Param("platform") String platform
  );

  public int deleteByUserIdAndProductId(
    @Param("userId") String userId,
    @Param("platform") String platform,
    @Param("product_id") Long product_id
  );

  public int save(
    @Param("userId") String userId,
    @Param("platform") String platform,
    @Param("product_id") Long product_id,
    @Param("quantity") int quantity
  );
}
