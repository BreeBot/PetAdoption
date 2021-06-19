package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptablePets;
import com.launchacademy.petadoption.models.PetTypes;
import com.launchacademy.petadoption.repositories.AdoptablePetsRepository;
import com.launchacademy.petadoption.repositories.PetTypesRepository;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class AdoptablePetsService {

  private AdoptablePetsRepository adoptablePetsRepo;
  private PetTypesRepository petTypesRepo;

  @Autowired
  public AdoptablePetsService(AdoptablePetsRepository adoptablePetsRepo,
      PetTypesRepository petTypesRepo) {
    this.adoptablePetsRepo = adoptablePetsRepo;
    this.petTypesRepo = petTypesRepo;
  }

//  public List<AdoptablePets> findAll() {
//
//    return (List<AdoptablePets>) adoptablePetsRepo.findAll();
//  }

  //this method passes to the controller
  public List<AdoptablePets> findAllByType(String type) {
    System.out.println(type);
    PetTypes typePet = petTypesRepo.findByTypeIgnoreCase(type);
    return adoptablePetsRepo.findAllByPetType(typePet.getId());
  }

  public AdoptablePets findById(String type, Integer id) {
    //getting pet type object from query to repo
    PetTypes petType = petTypesRepo.findByTypeIgnoreCase(type);
    //give me the id
    return (AdoptablePets) adoptablePetsRepo.findAllById(petType.getId(), id).orElseThrow(() -> new CantFindPetException());
  }
  @NoArgsConstructor
  private static class CantFindPetException extends RuntimeException {
  }
  @ControllerAdvice
  private class CantFindPetAdvice {
    @ResponseBody
    @ExceptionHandler(CantFindPetException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(CantFindPetException ex) {
      return "<h1>There is no pet matching the given type/id criteria. Make sure you input the correct information.</h1>";
    }
  }
}




