package com.greenfoxacademy.springherokutemplate.controller;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.repository.LocationRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search/api")
public class SearchRestController {
  LocationRepository locationRepository;

  public SearchRestController(LocationRepository locationRepository) {
      this.locationRepository = locationRepository;
  }

  @GetMapping("/locationlist")
  public List<Location> getLocationList() {
    return locationRepository.findAll();
  }
}