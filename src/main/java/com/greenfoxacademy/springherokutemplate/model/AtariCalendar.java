package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AtariCalendar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  private ServiceType serviceType;

  @ManyToOne
  private AtariProvider atariProvider;

  @ManyToOne
  private Location location;

  private String comment;

  @OneToMany(mappedBy = "atariCalendar")
  private Collection<TimeSlot> timeSlots = new ArrayList<>();


  public AtariCalendar(String name, ServiceType serviceType, AtariProvider atariProvider, String comment, Collection<TimeSlot> timeSlots, Location location) {
    this.serviceType = serviceType;
    this.atariProvider = atariProvider;
    this.comment = comment;
    this.timeSlots = timeSlots;
    this.name = name;
    this.location = location;
  }

  public void setTimeSlotSet(Collection<TimeSlot> timeSlots) {
    this.timeSlots = timeSlots;
  }
}
