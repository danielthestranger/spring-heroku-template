package com.greenfoxacademy.springherokutemplate.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeSlotDTO {
  private Long timeSlotId;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private boolean booked;
  private String comments;
  private String bookedByName;
}
