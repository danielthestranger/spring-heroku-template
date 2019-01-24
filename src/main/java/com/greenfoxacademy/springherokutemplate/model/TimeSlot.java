package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class TimeSlot {

  @org.hibernate.annotations.Parent
  private AtariCalendar atariCalendar;



}
