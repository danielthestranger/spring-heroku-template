package com.greenfoxacademy.springherokutemplate.service;

import java.util.ArrayList;
import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;
import com.greenfoxacademy.springherokutemplate.repository.AtariCalendarRepository;
import com.greenfoxacademy.springherokutemplate.repository.AtariProviderRepository;
import com.greenfoxacademy.springherokutemplate.repository.LocationRepository;
import com.greenfoxacademy.springherokutemplate.repository.ServiceTypeRepository;

import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
  LocationRepository locationRepository;
  AtariCalendarRepository atariCalendarRepository;
  ServiceTypeRepository serviceTypeRepository;
  AtariProviderRepository atariProviderRepository;

  public LocationServiceImpl(LocationRepository locationRepository, 
  AtariCalendarRepository atariCalendarRepository, ServiceTypeRepository serviceTypeRepository, AtariProviderRepository atariProviderRepository) {
    this.locationRepository = locationRepository;
    this.atariCalendarRepository = atariCalendarRepository;
    this.serviceTypeRepository = serviceTypeRepository;
    this.atariProviderRepository = atariProviderRepository;
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
  
  public List<AtariCalendar> getAtariCalendarsFromIds(List<Long> atariCalendarIds) {
    List<AtariCalendar> atariCalendars = new ArrayList<>();

    for (int i = 0; i < atariCalendarIds.size(); i++) {
      atariCalendars.add(atariCalendarRepository.findAllById(atariCalendarIds.get(i)));
    }

    return atariCalendars;
  }
  
  public AtariCalendar getAtariCalendarFromId(Long atariCalendarId) {
    return atariCalendarRepository.findAllById(atariCalendarId);
  }
  
  public ServiceType getServiceTypeFromId(Long serviceTypeId) {
    return serviceTypeRepository.findAllById(serviceTypeId);
  }
  
  public AtariProvider getAtariProviderFromId(Long atariProviderId) {
    return atariProviderRepository.findAllById(atariProviderId);
  }
  
  public List<ServiceType> getServiceTypesFromAtariCalendarIds(List<Long> atariCalendarIds) {
    List<ServiceType> serviceTypeList = new ArrayList<>();

    for (int i = 0; i < atariCalendarIds.size(); i++) {
      AtariCalendar atariCalendar = getAtariCalendarFromId(atariCalendarIds.get(i));
      ServiceType serviceType = getServiceTypeFromId(atariCalendar.getServiceType().getId());

      serviceTypeList.add(serviceType);
    }

    return serviceTypeList;
  }

  public ServiceType getServiceTypesFromAtariCalendarId(Long atariCalendarId) {
      AtariCalendar atariCalendar = getAtariCalendarFromId(atariCalendarId);
      ServiceType serviceType = getServiceTypeFromId(atariCalendar.getServiceType().getId());

    return serviceType;
  }

  public AtariProvider getServiceProviderFromAtariCalendarAndServiceTypeId(Long atariCalendarId, Long serviceTypeId) {
    AtariCalendar atariCalendar = getAtariCalendarFromId(atariCalendarId);
    ServiceType serviceType = getServiceTypeFromId(serviceTypeId);
    AtariProvider atariProvider = getAtariProviderFromId(atariCalendar.getAtariProvider().getId());

    if (atariCalendar.getServiceType().getId() == serviceType.getId()) {
	    return atariProvider;
    }
    return null;
  }
}