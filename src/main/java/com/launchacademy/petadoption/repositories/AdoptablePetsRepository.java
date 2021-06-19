package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.AdoptablePets;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptablePetsRepository extends PagingAndSortingRepository<AdoptablePets, Integer> {
  //querying my database and finding all pets by type, adding them to a list of adoptable pets. Then passing to service.
  @Query("select p from AdoptablePets p where type_id = :id ")
  List<AdoptablePets> findAllByPetType(@Param("id") Integer id);

  //querying the id, id1 looks at two parameters: type and id because just id looks at databaseId from url
  @Query("select p from AdoptablePets p where type_id = :id and id = :id1")
  Optional<Object> findAllById(@Param("id") Integer id, @Param("id1") Integer id1);

}
