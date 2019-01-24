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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(cascade = {CascadeType.ALL})
  private ServiceType serviceType;
  @ManyToOne(cascade = {CascadeType.ALL})
  private AtariProvider atariProvider;
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
