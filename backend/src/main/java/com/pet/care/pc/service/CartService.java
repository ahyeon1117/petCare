package com.pet.care.pc.service;

import com.pet.care.pc.dao.mapper.CartMapper;
import com.pet.care.pc.entitiy.shopping.cart.Cart;
import com.pet.care.pc.entitiy.user.id.UserId;
import com.pet.care.pc.utils.Utils;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired
  private CartMapper mapper;

  public List<Cart> findByUser(String param) {
    UserId userId = Utils.converUserId(param);
    return mapper.findByUserId(userId.getUserId(), userId.getPlatform());
  }

  @Transactional
  public void addToCart(String param, Long productId, int quantity) {
    UserId userId = Utils.converUserId(param);

    mapper.save(userId.getUserId(), userId.getPlatform(), productId, quantity);
  }

  @Transactional
  public void removeFromCart(String param, Long productId) {
    UserId userId = Utils.converUserId(param);
    mapper.deleteByUserIdAndProductId(
      userId.getUserId(),
      userId.getPlatform(),
      productId
    );
  }
}
