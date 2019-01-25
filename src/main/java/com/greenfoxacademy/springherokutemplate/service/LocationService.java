package com.greenfoxacademy.springherokutemplate.service;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;

public interface LocationService {
  List<Location> getAllLocations();

  List<LocationDTO> getAllLocationDTOs();

  LocationDTO locationToLocationDTOConverter(Location location);

  List<AtariCalendar> getAtariCalendarsFromIds(List<Long> atariCalendarIds);

  AtariCalendar getAtariCalendarFromId(Long atariCalendarId);

  List<ServiceType> getServiceTypesFromAtariCalendarIds(List<Long> atariCalendarIds);

  ServiceType getServiceTypesFromAtariCalendarId(Long atariCalendarId);

  AtariProvider getServiceProviderFromAtariCalendarAndServiceTypeId(Long calendarId, Long serviceTypeId);

  ServiceType getServiceTypeFromId(Long serviceTypeId)

  AtariProvider getAtariProviderFromId(Long atariProviderId)

  Location findCreateOrUpdate(String newName, Long id);

  Location findById(Long id);

  void deleteLocation(Long id);

  void saveLocation(Location location);

}