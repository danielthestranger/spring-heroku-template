package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
  private String city;
  private String street;
  private String houseNumber;
  private String zipCode;

  public Address(String city, String street, String houseNumber, String zipCode) {
    this.city = city;
    this.street = street;
    this.houseNumber = houseNumber;
    this.zipCode = zipCode;
  }
}
