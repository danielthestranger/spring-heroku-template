package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

  private ServiceTypeService serviceTypeService;

  @Autowired
  public AdminController(ServiceTypeService serviceTypeService) {
    this.serviceTypeService = serviceTypeService;
  }

  @GetMapping("/admin")
  public String adminPage(Model model) {
    model.addAttribute("ServiceTypes", serviceTypeService.findAll());
    return "admin";
  }
}
