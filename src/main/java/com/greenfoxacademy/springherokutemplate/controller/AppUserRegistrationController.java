package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.dto.RegistrationForm;
import com.greenfoxacademy.springherokutemplate.service.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(AppUserRegistrationController.CONTROLLER_ROOT)
public class AppUserRegistrationController {
  public static final String CONTROLLER_ROOT = "/register";

  private AppUserService userService;

  @Autowired
  public AppUserRegistrationController(AppUserService userService) {
      this.userService = userService;
  }

  @GetMapping
  public String registerForm() {
      return "register";
  }

  @PostMapping
  public String processRegistration(RegistrationForm regForm) {
      userService.createDefaultUser(regForm);
      return "redirect:/login";
  }
}
