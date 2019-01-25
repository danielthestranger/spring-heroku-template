package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.Location;
import com.greenfoxacademy.springherokutemplate.model.ServiceType;
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

  @Autowired
  public AdminController(ServiceTypeService serviceTypeService, LocationService locationService) {
    this.serviceTypeService = serviceTypeService;
    this.locationService = locationService;
  }

  @ModelAttribute
  public void addAllAttributeInputs(Model model) {
    model.addAttribute("newServicetype", new ServiceType());
    model.addAttribute("ServiceTypes", serviceTypeService.findAll());
    model.addAttribute("newLocation", new Location());
    model.addAttribute("locations", locationService.getAllLocations());

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
}
