package com.pet.care.pc.entitiy.shopping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Store {

  @Id
  private Long id;

  private String name;
  private String description;
  private String url;
}
