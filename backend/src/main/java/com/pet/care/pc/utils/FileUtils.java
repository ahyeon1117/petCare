package com.pet.care.pc.utils;

import com.pet.care.pc.enums.Connect;
import com.pet.care.pc.enums.StatusEnum;
import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

public class FileUtils {

  public StatusEnum upload(String path, MultipartFile mFile, Connect connect) {
    StatusEnum result = Connect.LOCAL.equals(connect)
      ? localUpload(path, mFile)
      : apiUpload(mFile);
    return result;
  }

  public File download(String path, Connect connect, String remoteUrl) {
    File result = Connect.LOCAL.equals(connect)
      ? localDownload(path, remoteUrl)
      : apiDownload(path, remoteUrl);
    return result;
  }

  // TODO : 수정 필요
  public StatusEnum apiUpload(MultipartFile mFile) {
    StatusEnum result = StatusEnum.COMPLETE;
    try {
      WebClient
        .builder()
        .baseUrl("주소")
        .build()
        .get()
        .retrieve()
        .onStatus(
          HttpStatusCode.valueOf(
            HttpStatus.INTERNAL_SERVER_ERROR.value()
          )::equals,
          response -> response.bodyToMono(String.class).map(Exception::new)
        );
    } catch (Exception e) {
      result = StatusEnum.FAILURE;
    }
    return result;
  }

  // TODO : 경로 파라미터 규격 정의
  public StatusEnum localUpload(String path, MultipartFile mFile) {
    // new File();
    return StatusEnum.COMPLETE;
  }

  public File apiDownload(String path, String remoteUrl) {
    // StatusEnum result = StatusEnum.COMPLETE;
    // try {
    // } catch (Exception e) {
    //   result = StatusEnum.FAILURE;
    // }
    // return result;
    return new File(path);
  }

  public File localDownload(String path, String remoteUrl) {
    return new File(path);
  }
}
