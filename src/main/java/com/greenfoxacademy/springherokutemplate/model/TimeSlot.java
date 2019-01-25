package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.greenfoxacademy.springherokutemplate.model.security.AppUser;

@Getter
@Setter
@Entity
public class TimeSlot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private AtariCalendar atariCalendar;

  private LocalDateTime beginTime;
  private LocalDateTime endTime;
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
