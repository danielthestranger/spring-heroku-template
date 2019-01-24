package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {

  @Id
  @GeneratedValue(generator = "ID_GENERATOR")
  private Long id;

  private String name;

  @OneToOne(cascade = {CascadeType.ALL})
  private Address address;

  @OneToMany(mappedBy = "location")
  private List<AtariCalendar> atariCalendars = new ArrayList<>();


  public Location(String name, Address address, List<AtariCalendar> atariCalendarList) {
    this.name = name;
    this.address = address;
    this.atariCalendars = atariCalendarList;
  }

}
