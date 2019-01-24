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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @ManyToMany
  private ServiceType serviceType;

  public AtariProvider(String name, ServiceType serviceType) {
    this.name = name;
    this.serviceType = serviceType;
  }
}
