package com.pet.care.pc.entitiy.shopping.product;

import com.pet.care.pc.entitiy.shopping.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Products {

  @Id
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store; // FK

  private String name;
  private String price;

  private String kind;

  private String description;
}
