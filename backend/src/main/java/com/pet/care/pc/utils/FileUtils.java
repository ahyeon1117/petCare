package com.pet.care.pc.utils;

import com.pet.care.pc.enums.StatusEnum;
import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class FileUtils {

  // TODO : 수정 필요 MultipartFile 전송 body 생성 필요
  public static StatusEnum apiUpload(String url, MultipartFile mFile) {
    StatusEnum result = StatusEnum.COMPLETE;
    try {
      WebClient
        .builder()
        .baseUrl(url)
        .build()
        .post()
        .contentType(MediaType.IMAGE_PNG)
        .body(BodyInserters.fromResource(mFile.getResource()))
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
  public static StatusEnum localUpload(String path, MultipartFile mFile) {
    return StatusEnum.COMPLETE;
  }

  public static File apiDownload(String path, String remoteUrl) {
    File file = null;
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
    return file;
  }

  public static File localDownload(String path, String remoteUrl) {
    return new File(path);
  }

  public static File generateFolder(String path) {
    File file = new File(path);
    if (!file.exists()) {
      file.mkdirs();
    }
    return file;
  }
}
