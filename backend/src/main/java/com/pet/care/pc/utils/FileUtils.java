package com.pet.care.pc.utils;

import com.pet.care.pc.enums.Connect;
import com.pet.care.pc.enums.StatusEnum;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

  public StatusEnum upload(MultipartFile mFile, Connect connect) {
    StatusEnum result = Connect.LOCAL.equals(connect)
      ? localUpload(mFile)
      : apiUpload(mFile);
    return result;
  }

  public File download(String path, Connect connect, String remoteUrl) {
    File result = Connect.LOCAL.equals(connect)
      ? localDownload(path, remoteUrl)
      : apiDownload(path, remoteUrl);
    return result;
  }

  public StatusEnum apiUpload(MultipartFile mFile) {
    return StatusEnum.COMPLETE;
  }

  public StatusEnum localUpload(MultipartFile mFile) {
    return StatusEnum.COMPLETE;
  }

  public File apiDownload(String path, String remoteUrl) {
    return new File(path);
  }

  public File localDownload(String path, String remoteUrl) {
    return new File(path);
  }
}
