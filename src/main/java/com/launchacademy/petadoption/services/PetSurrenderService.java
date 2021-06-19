package com.launchacademy.petadoption.services;


import com.launchacademy.petadoption.models.PetSurrender;
import com.launchacademy.petadoption.models.PetTypes;
import com.launchacademy.petadoption.repositories.PetSurrenderRepository;
import com.launchacademy.petadoption.repositories.PetTypesRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSurrenderService {
  private PetSurrenderRepository petSurrenderRepo;
  private PetTypesRepository petTypesRepo;

  @Autowired
  public PetSurrenderService(PetSurrenderRepository petSurrenderRepo, PetTypesRepository petTypesRepo) {
    this.petSurrenderRepo = petSurrenderRepo;
    this.petTypesRepo = petTypesRepo;
  }

  //save method, values coming from post mapping pet surrender controller
  //key value pair of pet stored as a Hash Map, passed form surrender form
  //create new empty application which we will populated with hash map data
  //then set the values in the model and communicate via the repo, repo passes to database
  //parse because boolean/integer
  public PetSurrender save(Map<String, String> pet) {
    PetTypes petType = petTypesRepo.findByTypeIgnoreCase(pet.get("petType"));
    PetSurrender petObject = new PetSurrender();
    petObject.setName(pet.get("name"));
    petObject.setPhoneNumber(pet.get("phoneNumber"));
    petObject.setEmail(pet.get("email"));
    petObject.setPetName(pet.get("petName"));
    petObject.setPetAge(Integer.parseInt(pet.get("petAge")));
    petObject.setType(petType);
    petObject.setImgUrl(pet.get("petImageUrl"));
    petObject.setVaccinationStatus(Boolean.parseBoolean(pet.get("vaccinationStatus")));
    return this.petSurrenderRepo.save(petObject);

  }
}












