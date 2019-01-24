package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

  private ServiceTypeService serviceTypeService;

  @Autowired
  public AdminController(ServiceTypeService serviceTypeService) {
    this.serviceTypeService = serviceTypeService;
  }

  @ModelAttribute
  public void addAllAttributeInputs(Model model) {
    model.addAttribute("newServicetype", new ServiceType());
    model.addAttribute("ServiceTypes", serviceTypeService.findAll());

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
  public String addLocation(@RequestParam(value = "servicetypeid", required = false) Long id,
                            @RequestParam(value = "newServiceType", required = false) String newName) {
    try {
      serviceTypeService.findCreateOrUpdate(newName, id);
    } catch (NullPointerException exception) {
      System.out.println("not found" + exception);
    }
    return "redirect:/admin/";
  }
}
