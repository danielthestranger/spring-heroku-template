package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AtariCalendar {

  @Id
  @GeneratedValue(generator = "ID_GENERATOR")
  private Long id;

  @ManyToOne
  private ServiceType serviceType;

  @ManyToOne
  private AtariProvider atariProvider;

  @ManyToOne
  private Location location;

  private String comment;

  @ElementCollection
  @CollectionTable(
          name = "calendar_timeslot",
          joinColumns = @JoinColumn(name = "ataricalendar_id")
  )
  private Set<TimeSlot> timeSlotSet = new HashSet<>();


  public AtariCalendar(ServiceType serviceType, AtariProvider atariProvider, String comment, Set<TimeSlot> timeSlotSet) {
    this.serviceType = serviceType;
    this.atariProvider = atariProvider;
    this.comment = comment;
    this.timeSlotSet = timeSlotSet;
  }

  public void setTimeSlotSet(Set<TimeSlot> timeSlotSet) {
    this.timeSlotSet = timeSlotSet;
  }
}
