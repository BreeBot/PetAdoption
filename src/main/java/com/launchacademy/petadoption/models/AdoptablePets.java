package com.launchacademy.petadoption.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class AdoptablePets {
  @Id
  @SequenceGenerator(name="adoptable_pets_generator", sequenceName = "adoptable_pets_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adoptable_pets_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @Column(name="name", nullable = false)
  private String name;

  @Column(name="img_url", nullable = false)
  private String imgUrl;

  @Column(name="age")
  private Integer age;

  @Column(name="vaccination_status")
  private Boolean vaccinationStatus;

  @Column(name="adoption_story", nullable = false)
  private String adoptionStory;

  @Column(name="adoption_status", nullable = false)
  private String adoptionStatus;

  //many adoptable can have one type_id
  @ManyToOne
  @JoinColumn(name="type_id", nullable = false)
  @JsonIgnoreProperties("adoptablePets")
  private PetTypes type;

}
