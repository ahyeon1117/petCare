package com.pet.care.pc.entitiy.user.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserId implements Serializable {

  String userId;
  String platform;
}
