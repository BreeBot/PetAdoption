package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.PetTypes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypesRepository extends PagingAndSortingRepository<PetTypes, Integer> {

  PetTypes findByTypeIgnoreCase(String type);
}
