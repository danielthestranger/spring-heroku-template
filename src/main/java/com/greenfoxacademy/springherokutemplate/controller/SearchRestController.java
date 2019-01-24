package com.greenfoxacademy.springherokutemplate.controller;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.service.LocationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search/api")
public class SearchRestController {
  LocationService locationService;

  public SearchRestController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("/locationlist")
  public List<Location> getLocationList() {
    return locationService.getAllLocations();
  }

  
}