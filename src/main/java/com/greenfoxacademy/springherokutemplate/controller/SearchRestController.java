package com.greenfoxacademy.springherokutemplate.controller;

import java.util.List;

import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.model.dto.LocationDTO;
import com.greenfoxacademy.springherokutemplate.service.LocationService;

import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.*;

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
  
  @GetMapping("/serviceprovidersoflocation")
  public AtariProvider getSelectedLocationServiceProvider(@RequestParam(value = "calendarid") Long calendarIdforProvider,
  @RequestParam(value = "servicetypeid") Long servicetypeId) {
    return locationService.getServiceProviderFromAtariCalendarAndServiceTypeId(calendarIdforProvider, servicetypeId);
  }

//  @PostMapping("/selectedideas")
//  public HttpResponse searchedValues(@RequestBody()) {
//  }
}