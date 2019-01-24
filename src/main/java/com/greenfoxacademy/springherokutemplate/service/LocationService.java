package com.greenfoxacademy.springherokutemplate.service;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;

public interface LocationService {
  public List<Location> getAllLocations();

  public List<LocationDTO> getAllLocationDTOs();

  public LocationDTO locationToLocationDTOConverter(Location location);

}