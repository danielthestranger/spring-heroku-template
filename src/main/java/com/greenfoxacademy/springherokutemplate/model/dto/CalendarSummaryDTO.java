package com.greenfoxacademy.springherokutemplate.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarSummaryDTO {
  private Long id;
  private String locationDescription;
  private String serviceTypeDescription;
  private String providerDescription;
}
