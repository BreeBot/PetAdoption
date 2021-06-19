package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.PetTypes;
import com.launchacademy.petadoption.repositories.PetTypesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetTypesService {
  private PetTypesRepository petTypesRepo;

  @Autowired
  public PetTypesService(PetTypesRepository petTypesRepo) {
    this.petTypesRepo = petTypesRepo;
  }

  public List<PetTypes> findAll() {
    return (List<PetTypes>) petTypesRepo.findAll();

  }

  public Integer findTypeId(String type) {
    //finding one type, not case sensitive
    PetTypes types = petTypesRepo.findByTypeIgnoreCase(type);
    Integer typesId = types.getId();
    return typesId;

  }

}
