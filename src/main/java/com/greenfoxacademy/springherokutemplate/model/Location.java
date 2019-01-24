package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private Address address;
  private List<AtariCalendar> atariCalendarList;

  public Location() {
    this.atariCalendarList = new ArrayList<AtariCalendar>();
  }

  public Location(String name, Address address, List<AtariCalendar> atariCalendarList) {
    this();
    this.name = name;
    this.address = address;
    this.atariCalendarList = atariCalendarList;
  }

}
