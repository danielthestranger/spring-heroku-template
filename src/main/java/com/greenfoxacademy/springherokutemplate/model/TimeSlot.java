package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.greenfoxacademy.springherokutemplate.model.security.AppUser;

@Getter
@Setter
@Embeddable
public class TimeSlot {

  @org.hibernate.annotations.Parent
  private AtariCalendar atariCalendar;

  private Timestamp beginTime;
  private Timestamp endTime;
  private boolean booked;
  private String comments;

  @ManyToOne
  private AppUser bookedBy;


  protected TimeSlot() {
  }

  //For some reason, Lombok doesn't generate a getter and setter for atariCalendar
  public AtariCalendar getAtariCalendar() {
    return atariCalendar;
  }

  //For some reason, Lombok doesn't generate a getter and setter for atariCalendar
  public void setAtariCalendar(AtariCalendar atariCalendar) {
    this.atariCalendar = atariCalendar;
  }
}
