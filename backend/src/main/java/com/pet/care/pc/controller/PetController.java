package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.Response;
import com.pet.care.pc.entitiy.pet.Pet;
import com.pet.care.pc.service.PetService;
import com.pet.care.pc.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/api/v1/pet")
public class PetController {

  @Autowired
  private PetService service;

  /*
   * if : LocalSave, RemoteSave
   */
  @PostMapping(
    value = "picture",
    produces = "application/json",
    consumes = "multipart/form-data"
  )
  public ResponseEntity<Response<Object>> imgUpload(
    @RequestBody MultipartFile data,
    @RequestParam("petId") Long petId
  ) {
    return null;
  }

  @GetMapping(value = "picture")
  public ResponseEntity<Response<Object>> imgDownload(
    @RequestParam("petId") Long petId
  ) {
    return null;
  }

  @Hidden
  @GetMapping(value = "all")
  @Operation(
    summary = "모든 애완동물 정보 조회",
    description = "모든 애완동물 정보 조회"
  )
  public ResponseEntity<Response<List<Pet>>> findAll() {
    try {
      List<Pet> pet = service.findAll();

      int resCode = ResponseUtils.getResCode(pet);
      return new ResponseEntity<>(
        (Response<List<Pet>>) ResponseUtils.response(null, pet),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @Operation(summary = "애완동물 정보 조회", description = "애완동물 정보 조회")
  public ResponseEntity<Response<Pet>> findByPetId(
    @RequestParam("petId") Long petId
  ) {
    try {
      Pet data = service.findByPetId(petId);
      int resCode = ResponseUtils.getResCode(data);

      return new ResponseEntity<>(
        (Response<Pet>) ResponseUtils.response(null, data),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
