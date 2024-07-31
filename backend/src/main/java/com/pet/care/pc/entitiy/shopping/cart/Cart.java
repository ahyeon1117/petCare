package com.pet.care.pc.entitiy.shopping.cart;

import com.pet.care.pc.entitiy.shopping.product.Product;
import com.pet.care.pc.entitiy.user.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Cart {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  private Users user;

  private int quantity;
}
