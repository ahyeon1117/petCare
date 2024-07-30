package com.pet.care.pc.entitiy.shopping.product;

import com.pet.care.pc.entitiy.shopping.Store;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Product {

  @Id
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store; // FK

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @NotNull
  private ProductImg productImg;

  private String name;
  private String price;
  private String img;

  private String kind;

  private String description;
}
