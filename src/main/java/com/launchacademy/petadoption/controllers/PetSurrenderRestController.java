package com.launchacademy.petadoption.controllers;

import com.launchacademy.petadoption.models.AdoptablePets;
import com.launchacademy.petadoption.models.PetSurrender;
import com.launchacademy.petadoption.repositories.AdoptablePetsRepository;
import com.launchacademy.petadoption.repositories.PetSurrenderRepository;
import com.launchacademy.petadoption.services.PetSurrenderService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/surrender")
public class PetSurrenderRestController {

  private PetSurrenderRepository petSurrenderRepo;
  private PetSurrenderService petSurrenderService;

  @Autowired
  public PetSurrenderRestController(PetSurrenderRepository petSurrenderRepo,  PetSurrenderService petSurrenderService) {
    this.petSurrenderRepo = petSurrenderRepo;
    this.petSurrenderService = petSurrenderService;
  }
//using request body to get all form values and adding to a map
    @PostMapping
    public ResponseEntity<PetSurrender> create(@RequestBody Map<String, String> pet) {
        return new ResponseEntity(petSurrenderService.save(pet), HttpStatus.OK);
      }

}



























