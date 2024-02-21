package com.pet.care.pc.entitiy.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Products {

  @Id
  private long id;

  private String name;
  private String price;
  private String description;
}
