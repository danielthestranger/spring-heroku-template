package com.greenfoxacademy.springherokutemplate.service;

import java.util.ArrayList;
import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;
import com.greenfoxacademy.springherokutemplate.repository.AtariCalendarRepository;
import com.greenfoxacademy.springherokutemplate.repository.LocationRepository;
import com.greenfoxacademy.springherokutemplate.repository.ServiceTypeRepository;

import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
  LocationRepository locationRepository;
  AtariCalendarRepository atariCalendarRepository;
  ServiceTypeRepository serviceTypeRepository;

  public LocationServiceImpl(LocationRepository locationRepository, 
  AtariCalendarRepository atariCalendarRepository, ServiceTypeRepository serviceTypeRepository) {
    this.locationRepository = locationRepository;
    this.atariCalendarRepository = atariCalendarRepository;
    this.serviceTypeRepository = serviceTypeRepository;
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

  public void saveLocation(Location location) {
    locationRepository.save(location);
  }

  @Override
  public Location findByName(String name) {
    return locationRepository.findByName(name);
  }

  @Override
  public void deleteLocation(Long id) {
    locationRepository.delete(locationRepository.findById(id).orElse(null));
  }

  @Override
  public Location findById(Long id) {
    return locationRepository.findById(id).orElse(null);
  }

  @Override
  public Location findCreateOrUpdate(String newName, Long id) {
    if (newName == null || newName.isEmpty()) {
      throw new NullPointerException("Attribute name is null or empty");
    }
    if (id == null) {
      Location savedLocation = locationRepository.findByName(newName);
      if (savedLocation == null) {
        Location location = new Location(newName);
        savedLocation = locationRepository.save(location);
        return savedLocation;
      } else if (savedLocation.getName().equals(newName)) {
        return savedLocation;
      }
    }
    Location updateLocation = locationRepository.findById(id).orElse(null);
    updateLocation.setName(newName);
    return locationRepository.save(updateLocation);
  }
}