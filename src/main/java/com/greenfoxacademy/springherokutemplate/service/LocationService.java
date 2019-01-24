package com.greenfoxacademy.springherokutemplate.service;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.repository.LocationRepository;

import org.springframework.stereotype.Service;

@Service
public class LocationService {
  LocationRepository locationRepository;

  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public List<Location> getAllLocations() {
    List<Location> locationList = locationRepository.findAll();
    return locationList;
  }
}