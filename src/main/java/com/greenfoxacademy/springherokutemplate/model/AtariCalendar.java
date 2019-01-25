package com.greenfoxacademy.springherokutemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AtariCalendar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private ServiceType serviceType;

  @ManyToOne
  private AtariProvider atariProvider;

  @ManyToOne
  private Location location;

  private String comment;

  @ElementCollection
  @CollectionTable(name = "calendar_timeslot")
  @org.hibernate.annotations.CollectionId(
      columns = @Column(name = "slot_id"),
      type = @org.hibernate.annotations.Type(type = "long"),
      generator = "TIMESLOT_ID_GENERATOR")
  private Collection<TimeSlot> timeSlotSet = new ArrayList<>();


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
