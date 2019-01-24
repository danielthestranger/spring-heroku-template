package com.greenfoxacademy.springherokutemplate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.greenfoxacademy.springherokutemplate.model.Address;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

  private Long id;
  private String name;
  private Address address;
  private List<Long> atariCalendarIds = new ArrayList<>();
}