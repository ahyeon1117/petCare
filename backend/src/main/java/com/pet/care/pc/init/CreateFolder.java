package com.pet.care.pc.init;

import jakarta.annotation.PostConstruct;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CreateFolder {

  @Value("${pet.path.profileImg}")
  private String petImgPath;

  @PostConstruct
  private void profileImgPathInit() {
    File petDir = new File(petImgPath);
    if (!petDir.exists()) {
      petDir.mkdirs();
    }
  }
}
