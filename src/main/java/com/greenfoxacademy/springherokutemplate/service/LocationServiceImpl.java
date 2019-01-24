package com.greenfoxacademy.springherokutemplate.service;

import java.util.ArrayList;
import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;
import com.greenfoxacademy.springherokutemplate.repository.LocationRepository;

import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
  LocationRepository locationRepository;

  public LocationServiceImpl(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public List<Location> getAllLocations() {
    List<Location> locationList = locationRepository.findAll();
    return locationList;
  }

  public List<LocationDTO> getAllLocationDTOs() {
    List<Location> locationList = getAllLocations();
    List<LocationDTO> locationDTOList = new ArrayList<>();

    for (int i = 0; i < locationList.size(); i++) {
      locationDTOList.add(locationToLocationDTOConverter(locationList.get(i)));
    }

    return locationDTOList;
  }

  public LocationDTO locationToLocationDTOConverter(Location location) {
    LocationDTO locationDTO = new LocationDTO();
    List<Long> atariCalendarIds = new ArrayList<>();

    locationDTO.setId(location.getId());
    locationDTO.setName(location.getName());
    locationDTO.setAddress(location.getAddress());

    for (int i = 0; i < location.getAtariCalendars().size(); i++) {
      atariCalendarIds.add(location.getAtariCalendars().get(i).getId());
    }

    locationDTO.setAtariCalendarIds(atariCalendarIds);

    return locationDTO;
  }
}