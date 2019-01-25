package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.service.AtariCalendarService;
import com.greenfoxacademy.springherokutemplate.service.AtariProviderService;
import com.greenfoxacademy.springherokutemplate.service.LocationService;
import com.greenfoxacademy.springherokutemplate.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

  private ServiceTypeService serviceTypeService;
  private LocationService locationService;
  private AtariProviderService atariProviderService;
  private AtariCalendarService atariCalendarService;

  @Autowired
  public AdminController(ServiceTypeService serviceTypeService, LocationService locationService,
                      AtariProviderService atariProviderService, AtariCalendarService atariCalendarService) {
    this.serviceTypeService = serviceTypeService;
    this.locationService = locationService;
    this.atariProviderService = atariProviderService;
    this.atariCalendarService = atariCalendarService;
  }

  @ModelAttribute
  public void addAllAttributeInputs(Model model) {
    model.addAttribute("newServicetype", new ServiceType());
    model.addAttribute("ServiceTypes", serviceTypeService.findAll());
    model.addAttribute("newLocation", new Location());
    model.addAttribute("locations", locationService.getAllLocations());
    model.addAttribute("atariproviders", atariProviderService.findAll());
    model.addAttribute("newAtariProvider", new AtariProvider());
    model.addAttribute("calendars", atariCalendarService.findAll());
    model.addAttribute("newAtariCalendar", new AtariCalendar());
  }


  @GetMapping("/admin")
  public String adminPage() {
    return "admin";
  }

  @GetMapping("admin/{id}/deleteServiceType")
  public String deleteServiceType(@PathVariable(value = "id") Long id) {
    serviceTypeService.deleteServiceType(id);
    return "redirect:/admin";
  }

  @GetMapping("admin/{id}/editServiceType")
  public String editServicetype(@PathVariable(value = "id") Long id, Model model) {
    ServiceType serviceType = serviceTypeService.findById(id);
    model.addAttribute("newServicetype", serviceType);
    return "admin";
  }

  @PostMapping("admin/addServiceType")
  public String addServiceType(@RequestParam(value = "servicetypeid", required = false) Long id,
                            @RequestParam(value = "newServiceType", required = false) String newName) {
    try {
      serviceTypeService.findCreateOrUpdate(newName, id);
    } catch (NullPointerException exception) {
      System.out.println("not found" + exception);
    }
    return "redirect:/admin/";
  }

  @GetMapping("admin/{id}/deleteLocation")
  public String deleteLocation(@PathVariable(value = "id") Long id) {
    locationService.deleteLocation(id);
    return "redirect:/admin";
  }

  @GetMapping("admin/{id}/editLocation")
  public String editLocation(@PathVariable(value = "id") Long id, Model model) {
    Location location = locationService.findById(id);
    model.addAttribute("newLocation", location);
    return "admin";
  }

  @PostMapping("admin/addLocation")
  public String addLocation(@RequestParam(value = "locationid", required = false) Long id,
                            @RequestParam(value = "newLocation", required = false) String newName) {
    try {
      locationService.findCreateOrUpdate(newName, id);
    } catch (NullPointerException exception) {
      System.out.println("not found" + exception);
    }
    return "redirect:/admin/";
  }

  @GetMapping("admin/{id}/deleteAtariProvider")
  public String deleteAtariProvider(@PathVariable(value = "id") Long id) {
    atariProviderService.deleteAtariProvider(id);
    return "redirect:/admin";
  }

  @GetMapping("admin/{id}/editAtariProvider")
  public String editAtariProvider(@PathVariable(value = "id") Long id, Model model) {
    AtariProvider atariProvider = atariProviderService.findById(id);
    model.addAttribute("newAtariProvider", atariProvider);
    return "admin";
  }

  @PostMapping("admin/addAtariProvider")
  public String addAtariProvider(@RequestParam(value = "providerid", required = false) Long id,
                            @RequestParam(value = "newAtariProvider", required = false) String newName) {
    try {
      atariProviderService.findCreateOrUpdate(newName, id);
    } catch (NullPointerException exception) {
      System.out.println("not found" + exception);
    }
    return "redirect:/admin/";
  }

  @GetMapping("admin/{id}/deleteAtariCalendar")
  public String deleteAtariCalendar(@PathVariable(value = "id") Long id) {
    atariCalendarService.deleteAtariCalendar(id);
    return "redirect:/admin";
  }

  @GetMapping("admin/{id}/editAtariCalendar")
  public String editAtariCalendar(@PathVariable(value = "id") Long id, Model model) {
    AtariCalendar atariCalendar = atariCalendarService.findById(id).orElse(null);
    model.addAttribute("newAtariCalendar", atariCalendar);
    return "admin";
  }

  @PostMapping("admin/addAtariCalendar")
  public String addAtariCalendar(@RequestParam(value = "calendarid", required = false) Long id,
                                 @RequestParam(value = "newAtariCalendar", required = false) String newName,
                                 @RequestParam(value = "newServicetypeName", required = false) String newServiceTypeName,
                                 @RequestParam(value = "newAtariProviderName", required = false) String newAtariProviderName,
                                 @RequestParam(value = "newLocationName", required = false) String newLocationName) {
    try {
      atariCalendarService.findCreateOrUpdate(newName, newServiceTypeName, newAtariProviderName, newLocationName, id);
    } catch (NullPointerException exception) {
      System.out.println("not found" + exception);
    }
    return "redirect:/admin/";
  }
}
