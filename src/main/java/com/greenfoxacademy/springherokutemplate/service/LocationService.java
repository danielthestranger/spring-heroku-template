package com.greenfoxacademy.springherokutemplate.service;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;

public interface LocationService {
  public List<Location> getAllLocations();

  public List<LocationDTO> getAllLocationDTOs();

  public LocationDTO locationToLocationDTOConverter(Location location);

  public List<AtariCalendar> getAtariCalendarsFromIds(List<Long> atariCalendarIds);

  public AtariCalendar getAtariCalendarFromId(Long atariCalendarId);

  public List<ServiceType> getServiceTypesFromAtariCalendarIds(List<Long> atariCalendarIds);

  ServiceType getServiceTypesFromAtariCalendarId(Long atariCalendarId);

}