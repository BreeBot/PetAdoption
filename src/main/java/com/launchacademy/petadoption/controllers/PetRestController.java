package com.launchacademy.petadoption.controllers;


import com.launchacademy.petadoption.models.AdoptablePets;
import com.launchacademy.petadoption.models.PetTypes;
import com.launchacademy.petadoption.repositories.AdoptablePetsRepository;
import com.launchacademy.petadoption.repositories.PetTypesRepository;
import com.launchacademy.petadoption.services.AdoptablePetsService;
import com.launchacademy.petadoption.services.PetTypesService;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
public class PetRestController {


  private PetTypesRepository petTypesRepo;
  private AdoptablePetsRepository adoptablePetsRepo;
  private PetTypesService petTypesService;
  private AdoptablePetsService adoptablePetsService;

  @Autowired
  public PetRestController(PetTypesRepository petTypesRepo, AdoptablePetsRepository adoptablePetsRepo, PetTypesService petTypesService, AdoptablePetsService adoptablePetsService) {
    this.petTypesRepo = petTypesRepo;
    this.adoptablePetsRepo = adoptablePetsRepo;
    this.petTypesService = petTypesService;
    this.adoptablePetsService = adoptablePetsService;
  }
//returning a pageable list of all types
  @GetMapping
  public Page<PetTypes> getPetTypes(Pageable pageable) {
    return petTypesRepo.findAll(pageable);
  }

  //returning a list of adoptable pets
  @GetMapping("/{type}")
  public List<AdoptablePets> findAllByType(@PathVariable String type) {
    //returning all types by list
    return adoptablePetsService.findAllByType(type);
  }
//beautiful code from Pat's marathon
  @GetMapping("/{type}/{id}")
  public AdoptablePets getAdoptablePets(@PathVariable String type, @PathVariable Integer id) {
    return adoptablePetsService.findById(type, id);
        //.orElseThrow(() -> new UrlNotFoundException());
  }

//  @NoArgsConstructor
//  private class UrlNotFoundException extends RuntimeException {
//  }
//
//  @ControllerAdvice
//  private class UrlNotFoundAdvice {
//    @ResponseBody
//    @ExceptionHandler(UrlNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    String urlNotFoundHandler(UrlNotFoundException ex){
//      return ex.getMessage();
//    }
//  }
}






















