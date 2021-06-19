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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pet_surrender_applications")
@NoArgsConstructor
@Getter
@Setter
public class PetSurrender {
  @Id
  @SequenceGenerator(name="pet_surrender_applications_generator", sequenceName = "pet_surrender_applications_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_surrender_applications_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @NotBlank(message = "can't be blank")
  @Column(name="name", nullable = false)
  private String name;

  @NotBlank(message = "please enter phone number")
  @Column(name="phone_number", nullable = false)
  private String phoneNumber;

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @NotBlank(message = "please enter an email")
  @Column(name="email", nullable = false)
  private String email;

  @NotBlank(message = "please enter a pet name")
  @Column(name="pet_name", nullable = false)
  private String petName;

  @NotBlank(message = "image can't be blank")
  @Column(name="pet_image_url", nullable = false)
  private String imgUrl;

  @Positive(message = "Must be a number above zero")
  @Column(name="pet_age")
  private Integer petAge;

  @NotNull(message = "Please select a vaxxed status")
  @Column(name="vaccination_status")
  private Boolean vaccinationStatus;

  @NotBlank(message = "must provide status")
  @Column(name="application_status", nullable = false)
  private String applicationStatus = "pending";

  //many pet surrenders can have one pet_type_id
  @ManyToOne
  @JoinColumn(name="pet_type_id", nullable = false)
  @JsonIgnoreProperties("petSurrenderApplications")
  private PetTypes type;

}
