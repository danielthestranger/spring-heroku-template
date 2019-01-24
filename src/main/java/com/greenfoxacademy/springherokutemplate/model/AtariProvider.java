package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AtariProvider {

  @Id
  @GeneratedValue(generator = "ID_GENERATOR")
  private Long id;

  private String description;

  //This can be linked to a user if necessary, if any service providers are also app users

  public AtariProvider(String description) {
    this.description = description;
  }
}
