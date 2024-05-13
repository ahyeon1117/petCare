package com.pet.care.pc.init;

import com.pet.care.pc.utils.FileUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Init {

  @Value("${pet.path.profileImg}")
  private String petImgPath;

  @PostConstruct
  private void profileImgPathInit() {
    FileUtils.generateFolder(petImgPath);
  }
}
