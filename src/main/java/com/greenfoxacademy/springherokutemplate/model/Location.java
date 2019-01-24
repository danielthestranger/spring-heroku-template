package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToOne(cascade = {CascadeType.ALL})
  private Address address;
  @OneToMany(cascade = {CascadeType.ALL})
  private List<AtariCalendar> atariCalendarList;

  public Location() {
    this.atariCalendarList = new ArrayList<>();
  }

  public Location(String name, Address address, List<AtariCalendar> atariCalendarList) {
    this();
    this.name = name;
    this.address = address;
    this.atariCalendarList = atariCalendarList;
  }

}
