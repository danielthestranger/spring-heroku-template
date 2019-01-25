package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.email.SendEmail;
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
  private SendEmail sendEmail;


  @Autowired
  public AppUserRegistrationController(AppUserService userService, SendEmail sendEmail) {
      this.userService = userService;
      this.sendEmail = sendEmail;

  }

  @GetMapping
  public String registerForm() {
      return "register";
  }

  @PostMapping
  public String processRegistration(RegistrationForm regForm) {
      userService.createDefaultUser(regForm);
      sendEmail.sendFromGMail(regForm.getEmail(), "Successful registration", "Dear " + regForm.getUsername() +
              ",\nWelcome to Atari bookings, you have successfully registrated.\n Best regards,\n Admin");

      return "redirect:/login";
  }
}
