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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/pet")
public class PetController {

  @Autowired
  private PetService service;

  @PostMapping(
    value = "picture",
    produces = "application/json",
    consumes = "multipart/form-data"
  )
  private ResponseEntity<Response<Object>> petPicture(
    @RequestBody MultiValueMap<String, String> data,
    @RequestParam("petId") Long petId
  ) {
    return null;
  }

  @Hidden
  @Operation(summary = "모든 Pet 정보 조회", description = "모든 Pet 정보 조회")
  @GetMapping(value = "info", produces = "application/json")
  private ResponseEntity<Response<List<Pet>>> findAll() {
    try {
      List<Pet> pet = service.findAll();

      int resCode = ResponseUtils.getResCode(pet);

      return new ResponseEntity<Response<List<Pet>>>(
        Response
          .<List<Pet>>builder()
          .message(pet)
          .error(null)
          .resCode(resCode)
          .build(),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @Operation(summary = "Pet 정보 조회", description = "Pet 정보 조회")
  @GetMapping(value = "info", produces = "application/json")
  private ResponseEntity<Response<Pet>> findByPetId(
    @RequestParam("petId") Long petId
  ) {
    try {
      Pet pet = service.findByPetId(petId);

      int resCode = ResponseUtils.getResCode(pet);

      return new ResponseEntity<Response<Pet>>(
        Response
          .<Pet>builder()
          .message(pet)
          .error(null)
          .resCode(resCode)
          .build(),
        HttpStatusCode.valueOf(resCode)
      );
    } catch (Exception e) {
      throw e;
    }
  }
}
