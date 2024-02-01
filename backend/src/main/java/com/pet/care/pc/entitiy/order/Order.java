package com.pet.care.pc.entitiy.order;

import com.pet.care.pc.enums.StatusEnum;
import java.time.LocalDateTime;

public class Order {

  private long id;
  private long userId;
  private LocalDateTime createdAt;
  private StatusEnum status;
}
