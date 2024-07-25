package com.pet.care.pc.controller;

import com.pet.care.pc.dto.api.CommonResponse;
import com.pet.care.pc.dto.api.PetSaveRequest;
import com.pet.care.pc.entitiy.pet.Pet;
import com.pet.care.pc.service.PetService;
import com.pet.care.pc.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/api/v1/pet")
public class PetController {

  @Autowired
  private PetService service;

  /*
   * if : LocalSave, RemoteSave
   */
  @PostMapping(value = "/save")
  public ResponseEntity<CommonResponse<String>> save(
    @RequestBody PetSaveRequest req
  ) {
    Pet pet = new Pet();
    BeanUtils.copyProperties(req, pet);
    try {
      service.save(pet);
      return new ResponseEntity<>(
        (CommonResponse<String>) ResponseUtils.response(null, "success"),
        HttpStatusCode.valueOf(200)
      );
    } catch (Exception e) {
      // TODO: handle exception
      return new ResponseEntity<>(
        (CommonResponse<String>) ResponseUtils.response(null, "fail"),
        HttpStatusCode.valueOf(400)
      );
    }
  }

  @GetMapping(value = "all")
  @Operation(
    summary = "모든 애완동물 정보 조회",
    description = "모든 애완동물 정보 조회"
  )
  public ResponseEntity<CommonResponse<List<Pet>>> findAll() {
    try {
      List<Pet> pet = service.findAll();

      int resCode = ResponseUtils.getResCode(pet);
      return new ResponseEntity<>(
        (CommonResponse<List<Pet>>) ResponseUtils.response(null, pet),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @GetMapping
  @Operation(summary = "애완동물 정보 조회", description = "애완동물 정보 조회")
  public ResponseEntity<CommonResponse<Pet>> findByPetId(
    @RequestParam("petId") Long petId
  ) {
    try {
      Pet data = service.findByPetId(petId);
      int resCode = ResponseUtils.getResCode(data);

      return new ResponseEntity<>(
        (CommonResponse<Pet>) ResponseUtils.response(null, data),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
