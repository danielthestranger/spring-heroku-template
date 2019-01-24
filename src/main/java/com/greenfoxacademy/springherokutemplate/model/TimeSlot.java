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
  private boolean isBooked;
  private String comments;

  @ManyToOne
  private AppUser appUser;

  protected TimeSlot() { }


  public AtariCalendar getAtariCalendar() {
    return atariCalendar;
  }

  public void setAtariCalendar(AtariCalendar atariCalendar) {
    this.atariCalendar = atariCalendar;
  }
}
