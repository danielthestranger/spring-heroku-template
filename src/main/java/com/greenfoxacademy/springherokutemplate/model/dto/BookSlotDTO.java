package com.greenfoxacademy.springherokutemplate.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookSlotDTO {
  @NotNull
  private Long timeSlotId;
}
