package com.greenfoxacademy.springherokutemplate.controller;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;
import com.greenfoxacademy.springherokutemplate.service.LocationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search/api")
public class SearchRestController {
  LocationService locationService;

  public SearchRestController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("/locationlist")
  public List<LocationDTO> getLocationList() {
    return locationService.getAllLocationDTOs();
  }

  @GetMapping("/servicetypesoflocation")
  public ServiceType getSelectedLocationServiceTypes(@RequestParam(value = "calendarid", required = true) Long calendarId) {
    return locationService.getServiceTypesFromAtariCalendarId(calendarId);
  }
  
  @GetMapping("/serviceproviderssoflocation")
  public AtariProvider getSelectedLocationServiceProvider(@RequestParam(value = "calendarid", required = true) Long calendarIdforProvider,
  @RequestParam(value = "servicetypeid", required = true) Long servicetypeId) {
    return locationService.getServiceProviderFromAtariCalendarAndServiceTypeId(calendarIdforProvider, servicetypeId);
  }
}