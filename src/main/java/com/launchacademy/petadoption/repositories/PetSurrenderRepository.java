package com.launchacademy.petadoption.repositories;


import com.launchacademy.petadoption.models.PetSurrender;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSurrenderRepository extends PagingAndSortingRepository<PetSurrender, Integer> {

}
